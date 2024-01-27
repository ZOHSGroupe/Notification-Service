package com.notification.exception;

public class NotValidNotification extends RuntimeException {
    public NotValidNotification(String message) {
        super(message);
    }
}
