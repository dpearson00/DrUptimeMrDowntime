package com.dumd.server.monitor.service.dynamodb.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.exceptions.ApplicationNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *  Accesses data for an application user {@link Application} to represent the model in DynamoDB
 */
@Singleton
public class ApplicationDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     *  Instantiates a ApplicationDao object
     *
     * @param dynamoDBMapper the {@link Application} used to interact with the 'applications' table
     */
    @Inject
    public ApplicationDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     *  Returns the {@link Application} corresponding to the specified appId
     *
     * @param appId a unique id of a given application
     * @return the stored Application, or throws an ApplicationNotFoundException if none was found.
     */
    public Application getApplication(String appId) {
        Application application = this.dynamoDBMapper.load(Application.class, appId);

        if (application == null) {
            throw new ApplicationNotFoundException(); // TODO add appropriate message
        }

        return application;
    }

    /**
     *  Takes in a {@link Application} object and saves it to the 'applications' table in DynamoDb
     * @param application A Application Object
     */
    public Application saveApplication(Application application) {
        dynamoDBMapper.save(application);
        // TODO: Time permitting, put in logic that makes sure info was saved in DB
        // Validation done in calling handleRequest
        return  application;
    }

    public Application deleteApplication(Application application) {
        dynamoDBMapper.delete(application);
        return application;
    }
}
