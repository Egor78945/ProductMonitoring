package com.example.authentication_service.model.user.status;

public enum UserStatusEnumeration {
    STATUS_ACTIVE(1), STATUS_BLOCKED(2), STATUS_DELETED(3), STATUS_FROZEN(4);
    private final long id;

    UserStatusEnumeration(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
