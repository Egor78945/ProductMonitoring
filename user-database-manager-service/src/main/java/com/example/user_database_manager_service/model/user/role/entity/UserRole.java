package com.example.user_database_manager_service.model.user.role.entity;

import java.util.Objects;
import java.util.UUID;

public class UserRole {
    private long id;
    private UUID userUUID;
    private long roleId;

    public UserRole(long id, UUID userUUID, long roleId) {
        this.id = id;
        this.userUUID = userUUID;
        this.roleId = roleId;
    }

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return roleId == userRole.roleId && Objects.equals(userUUID, userRole.userUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUUID, roleId);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userUUID=" + userUUID +
                ", roleId=" + roleId +
                '}';
    }
}
