package com.dumd.server.monitor.service.dynamodb.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.exceptions.UserNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 *  Accesses data for a user using {@link User} to represent the model in DynamoDB.
 */
@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     *  Instantiates a UserDao object
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the 'users' table
     */
    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     *  Returns the {@link User} corresponding to the user's email.
     *
     * @param userId for a given user
     * @return the stored User, or null if none was found.
     */
    public User getUser(String userId) {
        User user = this.dynamoDBMapper.load(User.class, userId);

        if (user == null) {
            throw new UserNotFoundException(String.format("Could not find user for userId: %s", userId));
        }

        return user;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withHashKeyValues(user)
                .withConsistentRead(false)
                .withIndexName(User.USER_BY_EMAIL_INDEX);

        return new ArrayList<>(dynamoDBMapper.query(User.class, queryExpression)).get(0);
    }

    /**
     * Check if a user already exists with given email address
     * @param email Email address to check in database
     * @return True if email already exist, False if it does not;
     */
    public boolean doesUserExist(String email) {
        try {
            getUserByEmail(email);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     *  Takes in a {@link User} object and saves it to the 'users' table in DynamoDB
     *
     * @param user a User object
     */
    public User saveUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }
}
