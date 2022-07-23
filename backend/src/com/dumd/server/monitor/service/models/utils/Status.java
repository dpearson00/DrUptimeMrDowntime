package com.dumd.server.monitor.service.models.utils;

public class Status {
    private StatusMessage status;
    private String code;

    public Status(StatusMessage status, String code) {
        this.status = status;
        this.code = code;
    }

    public StatusMessage getStatus() {
        return status;
    }

    public void setStatus(StatusMessage status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
