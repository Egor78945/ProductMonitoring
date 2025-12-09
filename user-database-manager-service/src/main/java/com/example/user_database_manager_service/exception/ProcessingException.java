package com.example.user_database_manager_service.exception;

public class ProcessingException extends RuntimeException {
    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(Object object) {
        this(String.format("processing exception: %s", object));
    }
}
