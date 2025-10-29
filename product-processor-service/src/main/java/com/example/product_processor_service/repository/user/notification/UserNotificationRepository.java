package com.example.product_processor_service.repository.user.notification;

import com.example.product_processor_service.model.notification.user.UserNotification;
import com.example.product_processor_service.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public abstract class UserNotificationRepository<UN> extends Repository<UN> {
    public abstract Optional<UserNotification> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);

    public abstract boolean existsByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);
}
