package com.dumd.server.monitor.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

/**
 *  Represents a record in the applications table.
 */
@DynamoDBTable(tableName = "applications")
public class Application {
    private String appId;
    private String appName;
    private String description;
    private String appUrl;
    private String userId;
    private String serverHistoryId;

    @DynamoDBHashKey(attributeName = "appId")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @DynamoDBAttribute(attributeName = "appName")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "appUrl")
    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "serverHistoryId")
    public String getServerHistoryId() {
        return serverHistoryId;
    }

    public void setServerHistoryId(String serverHistoryId) {
        this.serverHistoryId = serverHistoryId;
    }
}
