package com.example.product_processor_service.model.user.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class User {
    private Long id;
    private UUID uuid;
    private String email;
    private Long status_id;
    private Timestamp registered_at;

    public User(UUID uuid, String email, Long status_id, Timestamp registered_at) {
        this.uuid = uuid;
        this.email = email;
        this.status_id = status_id;
        this.registered_at = registered_at;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public Timestamp getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(Timestamp registered_at) {
        this.registered_at = registered_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(uuid, user.uuid) && Objects.equals(email, user.email) && Objects.equals(status_id, user.status_id) && Objects.equals(registered_at, user.registered_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, email, status_id, registered_at);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", email='" + email + '\'' +
                ", status_id=" + status_id +
                ", registered_at=" + registered_at +
                '}';
    }
}
