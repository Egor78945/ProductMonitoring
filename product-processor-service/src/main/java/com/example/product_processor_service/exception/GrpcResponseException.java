package com.example.product_processor_service.exception;

public class GrpcResponseException extends RuntimeException {
    public GrpcResponseException(String message) {
        super(message);
    }

    public GrpcResponseException(Throwable cause) {
        super(cause);
    }
}
