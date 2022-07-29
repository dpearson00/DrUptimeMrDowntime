package com.dumd.server.monitor.service.models.results;

import com.dumd.server.monitor.service.dynamodb.models.ServerHistory;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.utils.Status;

import java.util.List;

public class GetAppDetailsResult {
    private Status status;
    private ApplicationModel application;
    private List<List<String>> errorLogs;

    public GetAppDetailsResult(Builder builder) {
        this.status = builder.status;
        this.application = builder.application;
        this.errorLogs = builder.errorLogs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ApplicationModel getApplication() {
        return application;
    }

    public void setApplication(ApplicationModel application) {
        this.application = application;
    }

    public List<List<String>> getErrorLogs() {
        return errorLogs;
    }

    public void setErrorLogs(List<List<String>> errorLogs) {
        this.errorLogs = errorLogs;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Status status;
        private ApplicationModel application;
        private List<List<String>> errorLogs;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withApplication(ApplicationModel application) {
            this.application = application;
            return this;
        }

        public Builder withErrorLogs(List<List<String>> errorLogs) {
            this.errorLogs = errorLogs;
            return this;
        }

        public GetAppDetailsResult build() { return new GetAppDetailsResult(this);}
    }
}
