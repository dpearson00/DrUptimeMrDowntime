package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;

import javax.inject.Inject;

/**
 *  Implementation of the AddNewAppActivity for the DUMDServerMonitorService AddNewApp API.
 *
 *  This API allows a user to add a new application to be monitored.
 */
public class AddNewAppActivity implements RequestHandler<AddNewAppRequest, AddNewAppResult> {
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     *  Instantiates a new AddNewAppActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the applications table;
     */
    @Inject
    public AddNewAppActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by created a new application.
     *
     *  if the request is sent with malformed data, an InvalidInputException will be thrown.
     *
     * @param addNewAppRequest request object containing the application's information
     * @param context
     * @return addNewAppResult result object containing the API defined {@link ApplicationModel}
     */
    @Override
    public AddNewAppResult handleRequest(final AddNewAppRequest addNewAppRequest, Context context) {
        // TODO: validate data and store it in the users table. Then return a result.

        // Dummy return statement
        return null;
    }
}
