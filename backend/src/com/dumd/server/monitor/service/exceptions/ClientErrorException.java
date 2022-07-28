package com.dumd.server.monitor.service.exceptions;

/**
 *  Exception to throw if a client error is returned from the URL connection (400-499)
 */
public class ClientErrorException extends RuntimeException {
    private static final long serialVersionUID = -5012367952729224862L;

    public ClientErrorException() {
    }

    public ClientErrorException(String message) {
        super(message);
    }

    public ClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
