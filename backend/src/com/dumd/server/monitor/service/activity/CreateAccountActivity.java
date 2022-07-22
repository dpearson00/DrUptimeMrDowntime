package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.models.requests.LoginUserRequest;
import com.dumd.server.monitor.service.models.results.CreateAccountResult;
import com.dumd.server.monitor.service.models.UserModel;

import javax.inject.Inject;

/**
 *  Implementation of the CreateAccountActivity for the DUMDServerMonitorService CreateAccount API.
 *
 *  This API allows a customer to create an account.
 */
public class CreateAccountActivity implements RequestHandler<LoginUserRequest, CreateAccountResult> {
    private final UserDao userDao;

    /**
     * Instantiates a new CreateAccountActivity object
     *
     * @param userDao UserDao to access the users table
     */
    @Inject
    public CreateAccountActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     *  This method handles an incoming request by creating a new user account.
     *
     *  if the request is sent with malformed data, an InvalidInputException will be thrown.
     *
     * @param createAccountRequest request object containing the customer's information
     * @param context
     * @return createAccountResult result object containing the API defined {@link UserModel}
     */
    @Override
    public CreateAccountResult handleRequest(final LoginUserRequest createAccountRequest, Context context) {
        // TODO: validate data and store it in the users table. Then return a result.

        // Dummy return statement
        return null;
    }

}

