package com.example.user_database_manager_service.service.user.notification;

import com.example.user_database_manager_service.service.EntityService;

import java.util.Optional;
import java.util.UUID;

public interface UserNotificationService<UN> extends EntityService<UN> {
    Optional<UN> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);
}
