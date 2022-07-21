package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;

/**
 *  Implementation of the GetAppDetailsActivity for the DUMDServerMonitorService GetAppDetails API.
 *
 *  This API allows a user to get an application's details.
 */
public class GetAppDetailsActivity {
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     *  Instantiates a new GetAppDetailsActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the applications table
     */
    @Inject
    public GetAppDetailsActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by retrieving an applications details.
     *
     *  if the request is sent with an unknown appId, an ApplicationNotFoundException will be thrown.
     *
     * @param getAppDetailsRequest request object containing the appId
     * @param context
     * @return getAppDetailsResult result object containing the API defined {@link ApplicationModel}
     */
    @Override
    public GetAppDetailsResult handleRequest(final GetAppDetailsRequest getAppDetailsRequest, Context context) {
        // TODO: validate data and store it in the users table. Then return a result.

        // Dummy return statement
        return null;
    }
}
