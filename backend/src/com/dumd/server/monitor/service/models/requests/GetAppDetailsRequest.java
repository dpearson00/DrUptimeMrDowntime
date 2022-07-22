package com.dumd.server.monitor.service.models.requests;

import java.util.Objects;

public class GetAppDetailsRequest {
    private String userId;
    private String appId;

    public GetAppDetailsRequest(String userId, String appId) {
        this.userId = userId;
        this.appId = appId;
    }

    public GetAppDetailsRequest() {
    }

    public GetAppDetailsRequest(Builder builder) {
        this.userId = builder.userId;
        this.appId = builder.appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAppDetailsRequest that = (GetAppDetailsRequest) o;
        return userId.equals(that.userId) && appId.equals(that.appId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, appId);
    }

    @Override
    public String toString() {
        return "DeleteAppRequest{" +
                "userId='" + userId + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String userId;
        private String appId;

        private Builder() {}

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public GetAppDetailsRequest build() { return new GetAppDetailsRequest(this); }
    }
}