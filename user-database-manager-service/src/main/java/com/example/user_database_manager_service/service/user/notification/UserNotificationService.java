package com.example.user_database_manager_service.service.user.notification;

import com.example.user_database_manager_service.service.EntityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserNotificationService<UN> extends EntityService<UN> {
    void deleteAllByUserUuid(UUID userUuid);
    void deleteAllByNotificationTypeId(Long notificationTypeId);
    Optional<UN> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);
    boolean existsBy(UUID userUuid, Long notificationTypeId);
    boolean existsByUserUuid(UUID userUuid);
    boolean existsByNotificationTypeId(Long notificationTypeId);
}
