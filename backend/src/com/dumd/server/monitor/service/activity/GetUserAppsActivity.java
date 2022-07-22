package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.models.requests.GetUserAppsRequest;
import com.dumd.server.monitor.service.models.results.GetUserAppsResult;
import com.dumd.server.monitor.service.models.ApplicationModel;

import javax.inject.Inject;

/**
 *  Implementation fo the GetUserAppsActivity for the DUMDServerMonitorService GetUserApps API.
 *
 *  This API allows a customer to get all the apps associated with their account.
 */
public class GetUserAppsActivity implements RequestHandler<GetUserAppsRequest, GetUserAppsResult> {
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     *  Instantiates a new GetUserAppsActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the application table
     */
    @Inject
    public GetUserAppsActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by retrieving all of a user's apps.
     *
     *  if the request is sent with an unknown userId, an UnknownUserException will be thrown.
     *
     * @param getUserAppsRequest request object containing a userId
     * @param context
     * @return getUserAppsResult result object containing a list of {@link ApplicationModel}
     */
    @Override
    public GetUserAppsResult handleRequest(final GetUserAppsRequest getUserAppsRequest, Context context) {
        // TODO: validate data and store it in the users table. Then return a result.

        // Dummy return statement
        return null;
    }

}
