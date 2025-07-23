package com.example.authentication_service.exception.message;

public enum ExceptionMessage {
    FAILED_TO_CREATE("failed to create"), FAILED_TO_DELETE("failed to delete"), NOT_FOUND("not found"), ALREADY_EXISTS("already exists"), GRPC_ERROR("grpc error"), UNKNOWN("unknown error"), AUTHENTICATION_ERROR("authentication error");
    private final String message;
    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
