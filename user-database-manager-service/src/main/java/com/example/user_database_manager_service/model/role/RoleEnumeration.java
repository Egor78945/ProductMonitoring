package com.example.user_database_manager_service.model.role;

public enum RoleEnumeration {
    ROLE_USER(1), ROLE_ADMIN(2);
    private final long id;

    RoleEnumeration(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
