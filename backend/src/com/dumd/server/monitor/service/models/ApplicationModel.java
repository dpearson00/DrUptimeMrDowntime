package com.dumd.server.monitor.service.models;

import com.amazonaws.services.dynamodbv2.xspec.B;

import java.util.List;
import java.util.Objects;

public class ApplicationModel {
    private String appId;
    private String name;
    private String description;
    private String appUrl;              // or IP address
    private String userId;
    private List<String> serverHistoryId;

    public ApplicationModel() {

    }

    public ApplicationModel(Builder builder) {
        this. appId = builder.appId;
        this. name = builder.name;
        this.description = builder.description;
        this.appUrl = builder.appUrl;
        this.userId = builder.userId;
        this.serverHistoryId = builder.serverHistoryId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getServerHistoryId() {
        return serverHistoryId;
    }

    public void setServerHistoryId(List<String> serverHistoryId) {
        this.serverHistoryId = serverHistoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationModel that = (ApplicationModel) o;
        return Objects.equals(appId, that.appId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId, userId);
    }

    @Override
    public String toString() {
        return "ApplicationModel{" +
                "appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", appUrl='" + appUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", serverHistoryId=" + serverHistoryId +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String appId;
        private String name;
        private String description;
        private String appUrl;
        private String userId;
        private List<String> serverHistoryId;

        public Builder withAppId(String appIdToUse) {
            this.appId = appIdToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withDescription(String descriptionToUse) {
            this.description = descriptionToUse;
            return this;
        }

        public Builder withAppUrl(String appUrlToUse) {
            this.appUrl = appUrlToUse;
            return this;
        }

        public Builder withUserId(String userIdToUse) {
            this.userId = userIdToUse;
            return this;
        }

        public Builder withServerHistoryId(List<String> serverHistoryIdToUse) {
            this.serverHistoryId = serverHistoryIdToUse;
            return this;
        }

        public ApplicationModel build() { return new ApplicationModel(this); }
    }
}
