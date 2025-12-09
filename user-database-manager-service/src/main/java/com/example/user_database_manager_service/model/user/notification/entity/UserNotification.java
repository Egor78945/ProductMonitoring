package com.example.user_database_manager_service.model.user.notification.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class UserNotification {
    private UUID userUuid;
    private Long notificationTypeId;
    private Timestamp notifiedAt;

    public UserNotification(UUID userUuid, Long notificationTypeId, Timestamp notifiedAt) {
        this.userUuid = userUuid;
        this.notificationTypeId = notificationTypeId;
        this.notifiedAt = notifiedAt;
    }

    public UserNotification() {
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public Long getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Long notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public Timestamp getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(Timestamp notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserNotification that = (UserNotification) o;
        return Objects.equals(userUuid, that.userUuid) && Objects.equals(notificationTypeId, that.notificationTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUuid, notificationTypeId);
    }

    @Override
    public String toString() {
        return "UserNotification{" +
                "userUuid=" + userUuid +
                ", notificationTypeId=" + notificationTypeId +
                ", notifiedAt=" + notifiedAt +
                '}';
    }
}
