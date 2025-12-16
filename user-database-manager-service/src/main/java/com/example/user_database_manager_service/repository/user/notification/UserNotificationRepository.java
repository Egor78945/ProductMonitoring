package com.example.user_database_manager_service.repository.user.notification;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class UserNotificationRepository<UN> implements EntityRepository<UN> {
    public abstract Optional<UN> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);
}
