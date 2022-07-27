package com.dumd.server.monitor.service.event;

import com.amazonaws.services.lambda.runtime.Context;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.ServerHistoryDao;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.dynamodb.models.ServerHistory;
import com.dumd.server.monitor.service.exceptions.ApplicationConnectionException;
import com.dumd.server.monitor.service.exceptions.ClientErrorException;
import com.dumd.server.monitor.service.http.HTTPRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  Implementation of the CheckAppStatusEvent for the DUMDServerMonitorService AWS Eventbridge event
 */
public class CheckAppStatusEvent {
    private final Logger log = LogManager.getLogger();
    private final ApplicationDao applicationDao;
    private final ServerHistoryDao serverHistoryDao;

    /**
     *  Instantiates a new CheckAppStatusEvent object
     *
     * @param applicationDao ApplicationDao to access the application's table
     * @param serverHistoryDao ServerHistoryDao to access the serverHistory's table
     */
    @Inject
    public CheckAppStatusEvent(ApplicationDao applicationDao, ServerHistoryDao serverHistoryDao) {
        this.applicationDao = applicationDao;
        this.serverHistoryDao = serverHistoryDao;
    }

    /**
     *  This method will get a list of all applications in the applications table. It will then
     *  make a request to each appUrl and log the timestamp and status code in the serverHistory table
     *
     *  if a connection can't be made
     *
     * @param input
     * @param context
     * @return
     */
    public void handleRequest(Map<String, String> input, Context context) {
        log.info("Received event input {}", input);

        // Get a list of all the applications in the applications table
        List<Application> apps = applicationDao.getAllApplication();

        // For each application make a request to the server
        for (Application app : apps) {
            int responseCode;
            try {
                responseCode = HTTPRequest.checkStatus(app.getAppUrl());
            } catch (IOException e) {
                throw new ApplicationConnectionException("There was an error trying to connect with " + app);
            }

            // Once the request comes back, determine the status
            if (responseCode >= 400 && responseCode < 500) {
                throw new ClientErrorException("When trying to connect to " + app.getAppUrl() + " there was a " +
                        "client error");
            }
            // Save that status in the serverHistory database with a timestamp as the key
            ServerHistory sh = serverHistoryDao.getServerHistory(app.getServerHistoryId());
            Map<String, String> statusLog = sh.getErrorLogs();
            statusLog.put(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()).toString(),
                    String.valueOf(responseCode));
            serverHistoryDao.saveServerHistory(sh);

        }
    }
}
