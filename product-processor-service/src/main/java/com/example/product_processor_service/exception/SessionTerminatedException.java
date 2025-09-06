package com.example.product_processor_service.exception;

public class SessionTerminatedException extends RuntimeException {
    public SessionTerminatedException(String message) {
        super(message);
    }
    public SessionTerminatedException() {
        super("session terminated");
    }
}
