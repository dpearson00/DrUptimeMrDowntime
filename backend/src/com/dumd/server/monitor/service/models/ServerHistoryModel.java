package com.dumd.server.monitor.service.models;

import java.util.Map;
import java.util.Objects;

public class ServerHistoryModel {
    private String serverHistoryId;
    private String appId;
    private Map<String, String> errorLogs;      //<Timestamp, ErrorStatus>

    public ServerHistoryModel() {

    }

    public ServerHistoryModel(Builder builder) {
        this.serverHistoryId = builder.serverHistoryId;
        this.appId = builder.appId;
        this.errorLogs = builder.errorLogs;
    }

    public String getServerHistoryId() {
        return serverHistoryId;
    }

    public void setServerHistoryId(String serverHistoryId) {
        this.serverHistoryId = serverHistoryId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Map<String, String> getErrorLogs() {
        return errorLogs;
    }

    public void setErrorLogs(Map<String, String> errorLogs) {
        this.errorLogs = errorLogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerHistoryModel that = (ServerHistoryModel) o;
        return Objects.equals(serverHistoryId, that.serverHistoryId) && Objects.equals(appId, that.appId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverHistoryId, appId);
    }

    @Override
    public String toString() {
        return "ServerHistoryModel{" +
                "serverHistoryId='" + serverHistoryId + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String serverHistoryId;
        private String appId;
        private Map<String, String> errorLogs;

        public Builder withServerHistoryId(String serverHistoryIdToUse) {
            this.serverHistoryId = serverHistoryIdToUse;
            return this;
        }

        public Builder withAppId(String appIdToUse) {
            this.appId = appIdToUse;
            return this;
        }

        public Builder withErrorLogs(Map<String, String> errorLogsToUse) {
            this.errorLogs = errorLogsToUse;
            return this;
        }

        public ServerHistoryModel build() { return new ServerHistoryModel(this); }
    }
}
