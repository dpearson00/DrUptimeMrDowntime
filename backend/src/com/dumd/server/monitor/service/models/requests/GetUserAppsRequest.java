package com.dumd.server.monitor.service.models.requests;

import java.util.Objects;

public class GetUserAppsRequest {
    private String userId;

    public GetUserAppsRequest(String userId) {
        this.userId = userId;
    }

    public GetUserAppsRequest() {}

    public GetUserAppsRequest(Builder builder) {
        this.userId = builder.userId;
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
        GetUserAppsRequest that = (GetUserAppsRequest) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "GetUserAppsRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String userId;

        private Builder() {}

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetUserAppsRequest build() { return new GetUserAppsRequest(this); }
    }
}
