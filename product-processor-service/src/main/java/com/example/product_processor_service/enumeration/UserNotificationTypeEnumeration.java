package com.example.product_processor_service.enumeration;

public enum UserNotificationTypeEnumeration {
    PRODUCT_UPDATED(1);
    private final long id;
    UserNotificationTypeEnumeration(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
