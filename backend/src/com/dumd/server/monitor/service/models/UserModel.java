package com.dumd.server.monitor.service.models;

import java.util.List;
import java.util.Objects;

public class UserModel {
    private String userId;
    private String name;
    private String email;
    private String hashedPassword;
    private String phoneNumber;
    private List<String> appIds;

    public UserModel() {

    }

    public UserModel(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.email = builder.email;
        this.hashedPassword = builder.hashedPassword;
        this.phoneNumber = builder.phoneNumber;
        this.appIds = builder.appIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<String> appIds) {
        this.appIds = appIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserModels{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", appIds=" + appIds +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String userId;
        private String name;
        private String email;
        private String hashedPassword;
        private String phoneNumber;
        private List<String> appIds;

        public Builder withUserId(String userIdToUse) {
            this.userId = userIdToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withEmail(String emailToUse) {
            this.email = emailToUse;
            return this;
        }

        public Builder withHashedPassword(String hashedPasswordToUse) {
            this.hashedPassword = hashedPasswordToUse;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumberToUse) {
            this.phoneNumber = phoneNumberToUse;
            return this;
        }

        public Builder withAppIds(List<String> appIdsToUse) {
            this.appIds = appIdsToUse;
            return this;
        }

        public UserModel build() { return new UserModel(this); }
    }
}
