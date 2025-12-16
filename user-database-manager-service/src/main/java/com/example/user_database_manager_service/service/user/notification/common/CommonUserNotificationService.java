package com.example.user_database_manager_service.service.user.notification.common;

import java.util.UUID;

public interface CommonUserNotificationService {
    void deleteAllByUserUuid(UUID userUuid);
    void deleteAllByNotificationTypeId(Long notificationTypeId);
    void deleteAllByUserEmail(String email);
    boolean existsBy(UUID userUuid, Long notificationTypeId);
    boolean existsByUserUuid(UUID userUuid);
    boolean existsByNotificationTypeId(Long notificationTypeId);
}
