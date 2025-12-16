package com.example.user_database_manager_service.repository.user.notification.common;

import java.util.UUID;

public interface CommonUserNotificationRepository {
    void deleteAllByUserUuid(UUID userUuid);

    void deleteAllByUserEmail(String email);

    void deleteAllByNotificationTypeId(Long notificationTypeId);

    boolean existsBy(UUID userUuid, Long notificationTypeId);

    boolean existsByNotificationTypeId(Long notificationTypeId);

    boolean existsByUserUuid(UUID userUuid);
}
