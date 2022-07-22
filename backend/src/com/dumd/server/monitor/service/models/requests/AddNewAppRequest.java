package com.dumd.server.monitor.service.models.requests;

import java.util.Objects;

public class AddNewAppRequest {
    private String appName;
    private String appDescription;
    private String url;
    private String userId;

    public AddNewAppRequest(String appName, String appDescription, String url, String userId) {
        this.appName = appName;
        this.appDescription = appDescription;
        this.url = url;
        this.userId = userId;
    }

    public AddNewAppRequest() {
    }

    public AddNewAppRequest(Builder builder) {
        this.appName = builder.appName;
        this.appDescription = builder.appDescription;
        this.url = builder.url;
        this.userId = builder.userId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddNewAppRequest that = (AddNewAppRequest) o;
        return appName.equals(that.appName) && appDescription.equals(that.appDescription) && url.equals(that.url) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appName, appDescription, url, userId);
    }

    @Override
    public String toString() {
        return "AddNewAppRequest{" +
                "appName='" + appName + '\'' +
                ", appDescription='" + appDescription + '\'' +
                ", url='" + url + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder();}

    public static final class Builder {
        private String appName;
        private String appDescription;
        private String url;
        private String userId;

        public Builder withAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder withAppDescription(String appDescription) {
            this.appDescription = appDescription;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public AddNewAppRequest build() { return new AddNewAppRequest(this);}
    }
}
