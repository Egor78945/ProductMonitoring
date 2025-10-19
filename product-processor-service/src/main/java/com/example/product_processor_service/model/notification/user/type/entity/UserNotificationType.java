package com.example.product_processor_service.model.notification.user.type.entity;

import java.util.Objects;

public class UserNotificationType {
    private Long id;
    private String name;

    public UserNotificationType(String name) {
        this.name = name;
    }

    public UserNotificationType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserNotificationType that = (UserNotificationType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserNotificationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
