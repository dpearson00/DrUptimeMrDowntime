package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw when a user's password doesn't match the one in the database
 */
public class InvalidLoginException extends RuntimeException {

    private static final long serialVersionUID = 8120810597088403698L;

    public InvalidLoginException() {
    }

    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
