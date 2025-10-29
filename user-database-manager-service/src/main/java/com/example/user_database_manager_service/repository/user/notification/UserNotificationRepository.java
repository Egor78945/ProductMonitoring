package com.example.user_database_manager_service.repository.user.notification;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.UUID;

public abstract class UserNotificationRepository<UN> implements EntityRepository<UN> {
    public abstract void deleteAllByUserUuid(UUID userUuid);

    public abstract void deleteAllByNotificationTypeId(Long notificationTypeId);

    public abstract boolean existsBy(UUID userUuid, Long notificationTypeId);

    public abstract boolean existsByNotificationTypeId(Long notificationTypeId);

    public abstract boolean existsByUserUuid(UUID userUuid);
}
