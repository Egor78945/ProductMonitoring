package com.example.authentication_service.exception;

public class KeycloakException extends RuntimeException {
    public KeycloakException(String message) {
        super(message);
    }
}
