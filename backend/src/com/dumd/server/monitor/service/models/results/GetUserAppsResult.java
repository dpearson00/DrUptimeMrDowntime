package com.dumd.server.monitor.service.models.results;

import com.dumd.server.monitor.service.models.utils.Status;

import java.util.List;

public class GetUserAppsResult {
    private Status status;
    private List<String> appNames;
    private List<String> appIds;

    public List<String> getAppNames() {
        return appNames;
    }

    public void setAppNames(List<String> appNames) {
        this.appNames = appNames;
    }

    public List<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<String> appIds) {
        this.appIds = appIds;
    }

    public GetUserAppsResult(Builder builder) {
        this.status = builder.status;
        this.appNames = builder.appNames;
        this.appIds = builder.appIds;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Builder builder() { return new Builder();}

    public static final class Builder {
        private Status status;
        private List<String> appNames;
        private List<String> appIds;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withAppNames(List<String> appNames) {
            this.appNames = appNames;
            return this;
        }

        public Builder withAppIds(List<String> appIds) {
            this.appIds = appIds;
            return this;
        }

        public GetUserAppsResult build() { return new GetUserAppsResult(this);}
    }
}
