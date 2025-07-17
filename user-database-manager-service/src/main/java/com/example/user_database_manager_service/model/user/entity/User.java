package com.example.user_database_manager_service.model.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable {
    private long id;
    private UUID uuid;
    private String email;
    private long statusId;
    private Timestamp registeredAt;

    public User(long id, UUID uuid, String email, long statusId, Timestamp registeredAt) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.statusId = statusId;
        this.registeredAt = registeredAt;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(uuid, user.uuid) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", email='" + email + '\'' +
                ", statusId=" + statusId +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
