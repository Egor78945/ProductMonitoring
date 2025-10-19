package com.example.product_processor_service.enumeration;

public enum UserNotificationMessageEnumeration {
    PRODUCT_UPDATED("one of products, you have signed on, was updated!");
    private final String message;
    UserNotificationMessageEnumeration(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
