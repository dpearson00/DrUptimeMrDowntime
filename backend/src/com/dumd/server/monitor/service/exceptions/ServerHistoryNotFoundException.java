package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw when a given serverHistoryId is not found in the database
 */
public class ServerHistoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2881299397691122924L;

    public ServerHistoryNotFoundException() {
        super();
    }

    public ServerHistoryNotFoundException(String message) {
        super(message);
    }

    public ServerHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerHistoryNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ServerHistoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
