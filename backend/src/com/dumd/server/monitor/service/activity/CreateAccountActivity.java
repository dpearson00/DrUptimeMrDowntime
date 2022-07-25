package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.models.requests.CreateAccountRequest;
import com.dumd.server.monitor.service.models.results.CreateAccountResult;
import com.dumd.server.monitor.service.models.UserModel;
import com.dumd.server.monitor.service.models.utils.Status;
import com.dumd.server.monitor.service.models.utils.StatusMessage;
import com.dumd.server.monitor.service.utils.hashing.HashingUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 *  Implementation of the CreateAccountActivity for the DUMDServerMonitorService CreateAccount API.
 *
 *  This API allows a customer to create an account.
 */
public class CreateAccountActivity implements RequestHandler<CreateAccountRequest, CreateAccountResult> {
    private final Logger log = LogManager.getLogger();
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
    public CreateAccountResult handleRequest(final CreateAccountRequest createAccountRequest, Context context) {
        log.info("Received CreateAccountRequest {}", createAccountRequest);

        if (createAccountRequest.getEmail() == null) {
            throw new InvalidRequestException("No email present. Please enter valid email.");
        }

        if (createAccountRequest.getPassword() == null) {
            throw new InvalidRequestException("No password present. Please enter valid password.");
        }

        // TODO: Consider storing in hashedPassword and salt in original format instead of converting to hex
        byte[] salt = HashingUtil.createSalt();
        String saltHex = HashingUtil.bytesToHex(salt);
        String hashedPassword;

        try {
            hashedPassword = HashingUtil.generateHash(createAccountRequest.getPassword(), HashingUtil.SHA256, salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("The algorithm for the hashing function does not exist.");
        }

        User user = new User();
        user.setCustomerName(createAccountRequest.getCustomerName());
        user.setEmail(createAccountRequest.getEmail());
        user.setHashedPassword(hashedPassword);
        user.setSalt(saltHex);
        user.setUserId(String.valueOf(UUID.randomUUID()));
        user.setPhoneNumber(null);
        user.setAppIds(null);

        /*
        TODO: Add the ability to verify a user's email. This would entail sending an email
        with a code and having the user input the code and send it back to verify. A new table
        attribute called 'emailVerified' could be added and would be of boolean type.
         */

        userDao.saveUser(user);

        return CreateAccountResult.builder()
                .withStatus(new Status(StatusMessage.SUCCESS, "200"))
                .withMessage(String.format("New user created with userId %s", user.getUserId())).build();
    }

}

