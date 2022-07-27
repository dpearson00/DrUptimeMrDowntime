package com.dumd.server.monitor.service.dynamodb.daos;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.dumd.server.monitor.service.dynamodb.models.ServerHistory;
import com.dumd.server.monitor.service.exceptions.ServerHistoryNotFoundException;
import org.apache.logging.log4j.core.jmx.Server;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *  Accesses data for an application's server history using {@link ServerHistory} to represent the model in DynamoDB
 */
@Singleton
public class ServerHistoryDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     *  Instantiates a ServerHistoryDao object
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the 'serverHistory' table
     *
     */
    @Inject
    public ServerHistoryDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     *  Returns the {@link ServerHistory} corresponding to the specified serverHistoryId
     *
     * @param serverHistoryId a unique id ofr a given serverHistory
     * @return the stored ServerHistory, or null if none was found.
     */
    public ServerHistory getServerHistory(String serverHistoryId) {
        ServerHistory serverHistory = this.dynamoDBMapper.load(ServerHistory.class, serverHistoryId);

        if (serverHistory == null) {
            throw new ServerHistoryNotFoundException(); // TODO add appropriated message
        }

        return serverHistory;
    }

    /**
     *  Takes in a {@link ServerHistory} object and saves it to the 'serverHistory' table in DynamoDB
     *
     * @param serverHistory a ServerHistory object
     */
    public ServerHistory saveServerHistory(ServerHistory serverHistory) {
        dynamoDBMapper.save(serverHistory);
        return serverHistory;

    }

    public void deleteServerHistory(ServerHistory serverHistory) {
        dynamoDBMapper.delete(serverHistory);
    }
}
