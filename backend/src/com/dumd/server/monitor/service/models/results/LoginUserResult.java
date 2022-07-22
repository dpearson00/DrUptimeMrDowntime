package com.dumd.server.monitor.service.models.results;

import com.dumd.server.monitor.service.models.UserModel;
import com.dumd.server.monitor.service.models.utils.Status;

public class LoginUserResult {
    private Status status;
    private UserModel user;

    public LoginUserResult(Builder builder) {
        this.status = builder.status;
        this.user = builder.user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Builder builder() { return new Builder(); }

    public static final class Builder {
        private Status status;
        private UserModel user;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withUser(UserModel user) {
            this.user = user;
            return this;
        }

        public LoginUserResult build() { return new LoginUserResult(this);}
    }
}
