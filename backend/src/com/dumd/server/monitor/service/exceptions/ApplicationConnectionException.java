package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw when there is an error trying to check a status of an Application
 */
public class ApplicationConnectionException extends RuntimeException {
    private static final long serialVersionUID = 8479897279163336020L;

    public ApplicationConnectionException() {
    }

    public ApplicationConnectionException(String message) {
        super(message);
    }

    public ApplicationConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
