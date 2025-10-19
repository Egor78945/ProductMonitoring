package com.example.product_processor_service.service.notification.user;

import com.example.product_processor_service.model.notification.user.UserNotification;

import java.util.UUID;

public interface UserNotificationService<U extends UserNotification> {
    U save(U userNotification);
    U update(U userNotification);
    U findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);
    boolean existsByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId);
}
