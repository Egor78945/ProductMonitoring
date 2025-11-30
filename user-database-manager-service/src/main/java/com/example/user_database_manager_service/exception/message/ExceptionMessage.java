package com.example.user_database_manager_service.exception.message;

public enum ExceptionMessage {
    ALREADY_EXISTS("already exists"), NOT_FOUND("not found"), FAILED_TO_CREATE("failed to create"), UNEXPECTED_ERROR("unexpected error");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
