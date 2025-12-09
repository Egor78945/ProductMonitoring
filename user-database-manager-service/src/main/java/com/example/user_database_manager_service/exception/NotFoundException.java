package com.example.user_database_manager_service.exception;

public class NotFoundException extends ProcessingException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Object object) {
        this(String.format("not found: %s", object));
    }
}
