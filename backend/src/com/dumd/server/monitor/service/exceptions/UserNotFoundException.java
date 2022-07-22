package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw when a given userId is not found in the database
 */
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1096746422160058161L;

    public UserNotFoundException() { super();}

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
