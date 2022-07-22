package com.dumd.server.monitor.service.models.results;

import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.utils.Status;

import java.util.List;

public class GetUserAppsResult {
    private Status status;
    private List<ApplicationModel> applications;

    public GetUserAppsResult(Builder builder) {
        this.status = builder.status;
        this.applications = builder.applications;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ApplicationModel> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationModel> applications) {
        this.applications = applications;
    }

    public Builder builder() { return new Builder();}

    public static final class Builder {
        private Status status;
        private List<ApplicationModel> applications;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withApplications(List<ApplicationModel> applications) {
            this.applications = applications;
            return this;
        }

        public GetUserAppsResult build() { return new GetUserAppsResult(this);}
    }
}
