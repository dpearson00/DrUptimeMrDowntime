package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.exceptions.InvalidLoginException;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.models.requests.LoginUserRequest;
import com.dumd.server.monitor.service.models.results.LoginUserResult;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.UserModel;
import com.dumd.server.monitor.service.models.utils.Status;
import com.dumd.server.monitor.service.models.utils.StatusMessage;
import com.dumd.server.monitor.service.utils.converters.ModelConverterUtil;
import com.dumd.server.monitor.service.utils.hashing.HashingUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;

/**
 *  Implementation of the LoginUserActivity for the DUMDServerMonitorService LoginUser API.
 *
 *  This API allows a user to log in to their account.
 */
public class LoginUserActivity implements RequestHandler<LoginUserRequest, LoginUserResult> {
    private final Logger log = LogManager.getLogger();
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
        log.info("Received LoginUserRequest {}", loginUserRequest);

        if (loginUserRequest.getEmail() == null) {
            throw new InvalidRequestException("No email present. Please enter valid email.");
        }

        if (loginUserRequest.getPassword() == null) {
            throw new InvalidRequestException("No password present. Please enter valid password.");
        }

        User user = userDao.getUserByEmail(loginUserRequest.getEmail());
        boolean success;
        try {
            success = user.getHashedPassword().equals(HashingUtil.generateHash(loginUserRequest.getPassword(),
                    HashingUtil.SHA256, HashingUtil.hexStringToByteArray(user.getSalt())));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("The algorithm for the hashing function does not exist.");
        }

        if (success) {
            return LoginUserResult.builder()
                    .withStatus(new Status(StatusMessage.SUCCESS, "200"))
                    .withUser(ModelConverterUtil.toUserModel(user))
                    .build();
        } else {
            throw new InvalidLoginException("The email and password do not match.");
        }
    }
}
