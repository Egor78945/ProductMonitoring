package com.example.user_database_manager_service.exception;

public class AlreadyExistsException extends ProcessingException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(Object object) {
        this(String.format("already exists: %s", object));
    }
}
