package com.example.user_database_manager_service.model.account.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class Account {
    private long id;
    private UUID uuid;
    private String name;
    private UUID userUUID;
    private long statusId;
    private Timestamp createdAt;
    private boolean main;

    public Account(long id, UUID uuid, String name, UUID userUUID, long statusId, Timestamp createdAt, boolean main) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.userUUID = userUUID;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.main = main;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
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

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(uuid, account.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", userUUID=" + userUUID +
                ", statusId=" + statusId +
                ", createdAt=" + createdAt +
                ", main=" + main +
                '}';
    }
}
