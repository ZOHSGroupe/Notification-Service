package com.notification.exception;

public class ClientNotExistsException extends RuntimeException {
    public ClientNotExistsException(String message) {
        super(message);
    }
}