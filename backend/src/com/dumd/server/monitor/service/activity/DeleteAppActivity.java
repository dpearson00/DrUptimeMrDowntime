package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.models.requests.DeleteAppRequest;
import com.dumd.server.monitor.service.models.results.DeleteAppResult;
import com.dumd.server.monitor.service.models.UserModel;

import javax.inject.Inject;

import com.dumd.server.monitor.service.models.utils.Status;
import com.dumd.server.monitor.service.models.utils.StatusMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *  Implementation of the DeleteAppActivity for the DUMDServerMonitorService DeleteApp API.
 *
 *  This API allows a customer to create an account.
 */
public class DeleteAppActivity implements RequestHandler<DeleteAppRequest, DeleteAppResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     *  Instantiates a new DeleteAppActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the application table
     */
    @Inject
    public DeleteAppActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by deleting the app from the table
     *  and removing any notion of the app from the corresponding users table
     *
     *  if the request is sent with unknown appId, a ApplicationNotFoundException will be thrown.
     *
     * @param deleteAppRequest request object containing the user's information and the app
     *                         to be deleted information
     * @param context
     * @return deleteAppResult result object containing the API defined {@link UserModel}
     */
    @Override
    public DeleteAppResult handleRequest(final DeleteAppRequest deleteAppRequest, Context context) {
        log.info("Received DeleteAppRequest {}", deleteAppRequest);

        if(deleteAppRequest.getUserId() == null) {
            throw new InvalidRequestException("No User Id present. Please enter valid User Id.");
        }

        if(deleteAppRequest.getAppId() == null) {
            throw new InvalidRequestException("No App Id present. Please enter valid App Id.");
        }

        Application app = applicationDao.getApplication(deleteAppRequest.getAppId());

        if(!app.getAppId().equals(deleteAppRequest.getAppId())) {
            throw new InvalidRequestException(
                    String.format("Requested Application Id: %s and " +
                            "returned Application Id: %s, do not match."
                            ,deleteAppRequest.getAppId(), app.getAppId()));
        }

        applicationDao.deleteApplication(app);

        return DeleteAppResult.builder()
                .withStatus(new Status(StatusMessage.SUCCESS, "200"))
                .withMessage(String.format("Application Id: %s successfully deleted.", app.getAppId()))
                .build();
    }
}
