package com.example.authentication_service.exception.message;

public enum ExceptionMessage {
    FAILED_TO_CREATE("failed to create"), NOT_FOUND("not found");
    private final String message;
    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
