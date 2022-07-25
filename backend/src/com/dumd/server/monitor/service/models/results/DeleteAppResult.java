package com.dumd.server.monitor.service.models.results;

import com.dumd.server.monitor.service.models.utils.Status;

public class DeleteAppResult {
    private Status status;
    private String message;

    public DeleteAppResult(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Status status;
        private String message;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public DeleteAppResult build() { return new DeleteAppResult(this);}
    }
}
