package com.example.authentication_service.model.account.status;

public enum AccountStatusEnumeration {
    STATUS_ACTIVE(1), STATUS_BLOCKED(2), STATUS_DELETED(3), STATUS_FROZEN(4);
    private final long id;

    AccountStatusEnumeration(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}

