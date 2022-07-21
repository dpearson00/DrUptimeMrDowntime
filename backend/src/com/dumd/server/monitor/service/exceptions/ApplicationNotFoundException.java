package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw when a given appId is not found in the database
 */
public class ApplicationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6542861368565929706L;

    public ApplicationNotFoundException() {
        super();
    }

    public ApplicationNotFoundException(String message) {
        super(message);
    }

    public ApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
