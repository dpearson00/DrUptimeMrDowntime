package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.ServerHistoryDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.dynamodb.models.ServerHistory;
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
 *  Implementation of the CheckAppStatusActivity for the DUMDServerMonitorService AWS Eventbridge event
 */
public class CheckAppStatusActivity implements RequestHandler<Map<String,String>, String> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final ApplicationDao applicationDao;
    private final ServerHistoryDao serverHistoryDao;

    /**
     *  Instantiates a new CheckAppStatusActivity object
     *
     * @param userDao UserDao to access the user's table
     * @param applicationDao ApplicationDao to access the application's table
     * @param serverHistoryDao ServerHistoryDao to access the serverHistory's table
     */
    @Inject
    public CheckAppStatusActivity(UserDao userDao, ApplicationDao applicationDao, ServerHistoryDao serverHistoryDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
        this.serverHistoryDao = serverHistoryDao;
    }

    /**
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        log.info("Received event input {}", input);
        // Get a list of all the applications in the applications table

        List<Application> apps = applicationDao.getAllApplication();

        // For each application make a request to the server

        for (Application app : apps) {
            int responseCode;
            try {
                responseCode = HTTPRequest.checkAvailability(app.getAppUrl());
            } catch (IOException e) {
                throw new RuntimeException("There was an error trying to connect with " + app);
            }

            // Once the request comes back, determine the status
            if (responseCode >= 400 && responseCode < 500) {
                throw new RuntimeException("When trying to connect to " + app.getAppUrl() + " there was a " +
                        "client error");
            }
            // Save that status in the serverHistory database with a timestamp as the key
            ServerHistory sh = serverHistoryDao.getServerHistory(app.getServerHistoryId());
            Map<String, String> statusLog = sh.getErrorLogs();
            statusLog.put(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()).toString(),
                    String.valueOf(responseCode));
            serverHistoryDao.saveServerHistory(sh);

        }
        // Once all apps are checked return a message stating that the task is complete
        return "Finished checking all apps";

    }
}
