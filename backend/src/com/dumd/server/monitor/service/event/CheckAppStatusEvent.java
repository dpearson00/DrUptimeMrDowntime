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
import java.util.ArrayList;
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
    public void handleRequest(Object input, Context context) {
        log.info("Received event input {}", input);

        // Get a list of all the applications in the applications table
        List<Application> apps = applicationDao.getAllApplication();

        // For each application make a request to the server
        // TODO: add a timeout in case there is no connection to the website
        for (Application app : apps) {
            int responseCode;
            responseCode = HTTPRequest.checkStatus(app.getAppUrl());

            // if an IOException is thrown, a warning will be logged but the loop will keep running.
            if (responseCode == -2) {
                log.warn("There was an error trying to connect with " + app);
                continue;
            }

            // Once the request comes back, determine the status
            if (responseCode >= 400 && responseCode < 500) {
                // If there is a client error (400-499) a warning will be logged and the loop will continue.
                log.warn("When trying to connect to " + app.getAppUrl() + " there was a " +
                        "client error");
            }
            // Save that status in the serverHistory database with a timestamp as the key
            ServerHistory sh = serverHistoryDao.getServerHistory(app.getServerHistoryId());
            List<List<String>> statusLogs = sh.getErrorLogs();
            List<String> statusLog = new ArrayList<>();
            statusLog.add(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()).toString());
            statusLog.add(String.valueOf(responseCode));
            statusLogs.add(statusLog);
            sh.setErrorLogs(statusLogs);
            serverHistoryDao.saveServerHistory(sh);

        }
    }
}
