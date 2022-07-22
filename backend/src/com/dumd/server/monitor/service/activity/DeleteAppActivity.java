package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.models.requests.DeleteAppRequest;

import javax.inject.Inject;

/**
 *  Implementation of the DeleteAppActivity for the DUMDServerMonitorService DeleteApp API.
 *
 *  This API allows a customer to create an account.
 */
public class DeleteAppActivity implements RequestHandler<DeleteAppRequest, DeleteAppResult> {
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
        // TODO: validate data and store it in the users table. Then return a result.

        // Dummy return statement
        return null;
    }
}
