package com.example.authentication_service.model.user.role;

public enum UserRoleEnumeration {
    ROLE_USER(1), ROLE_ADMIN(2);
    private final long id;

    UserRoleEnumeration(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
