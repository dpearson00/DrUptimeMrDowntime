package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.models.requests.LoginUserRequest;
import com.dumd.server.monitor.service.models.results.LoginUserResult;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.UserModel;

import javax.inject.Inject;

/**
 *  Implementation of the LoginUserActivity for the DUMDServerMonitorService LoginUser API.
 *
 *  This API allows a user to log in to their account.
 */
public class LoginUserActivity implements RequestHandler<LoginUserRequest, LoginUserResult> {
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     * Instantiates a new LoginUserActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the applications table
     */
    @Inject
    public LoginUserActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by logging in a user.
     *
     *  if the request has unknown credentials, a UserNotFoundException will be thrown.
     *
     * @param loginUserRequest request object containing the users email and password.
     * @param context
     * @return loginUserResult result object containing the API defined {@link UserModel}
     *         and a list of {@link ApplicationModel}
     */
    @Override
    public LoginUserResult handleRequest(final LoginUserRequest loginUserRequest, Context context) {
        // TODO: validate data and store it in the users table. Then return a result.

        // Dummy return statement
        return null;
    }
}
