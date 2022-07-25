package com.dumd.server.monitor.service.dynamodb.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.exceptions.UserNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

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
            throw new UserNotFoundException(); // TODO add appropriate message
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
     *  Takes in a {@link User} object and saves it to the 'users' table in DynamoDB
     *
     * @param user a User object
     */
    public User saveUser(User user) {
        dynamoDBMapper.save(user);
        // TODO: Time permitting, put in logic that makes sure info was saved in DB
        return user;
    }
}
