package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw when a given input is invalid or missing.
 */
public class InvalidRequestException extends RuntimeException {
    private static final long serialVersionUID = 7471494371658333094L;

    public InvalidRequestException() { super(); }

    public InvalidRequestException(String message) { super(message); }

    public InvalidRequestException(String message, Throwable cause) { super(message, cause); }

    public InvalidRequestException(Throwable cause) { super(cause); }

    public InvalidRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
