package com.dumd.server.monitor.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

/**
 *  Represents a record in the users table.
 */
@DynamoDBTable(tableName = "users")
public class User {

    public static final String USER_BY_EMAIL_INDEX = "UserByEmailIndex";
    private String userId;
    private String customerName;
    private String email;
    private String salt;
    private String hashedPassword;
    private String phoneNumber;
    private List<String> appIds;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "customerName")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @DynamoDBIndexHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @DynamoDBAttribute(attributeName = "hashedPassword")
    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @DynamoDBAttribute(attributeName = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @DynamoDBAttribute(attributeName = "appIds")
    public List<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<String> appIds) {
        this.appIds = appIds;
    }
}
