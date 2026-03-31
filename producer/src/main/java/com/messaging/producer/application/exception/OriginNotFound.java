package com.messaging.producer.application.exception;

public class OriginNotFound extends RuntimeException {
    public OriginNotFound(String message) {
        super(message);
    }
}
