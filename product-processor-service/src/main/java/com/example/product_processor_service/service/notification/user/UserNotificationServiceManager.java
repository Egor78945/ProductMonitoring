package com.example.product_processor_service.service.notification.user;

import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.model.notification.user.UserNotification;
import com.example.product_processor_service.repository.user.notification.UserNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserNotificationServiceManager implements UserNotificationService<UserNotification> {
    private final UserNotificationRepository<UserNotification> userNotificationRepository;

    public UserNotificationServiceManager(UserNotificationRepository<UserNotification> userNotificationRepository) {
        this.userNotificationRepository = userNotificationRepository;
    }

    @Override
    public UserNotification save(UserNotification userNotification) {
        if (!existsByUserUuidAndNotificationTypeId(userNotification.getUserUuid(), userNotification.getNotificationTypeId())) {
            return userNotificationRepository.save(userNotification);
        }
        throw new AlreadyExistsException(String.format("user notification already exists: %s", userNotification));
    }

    @Override
    public UserNotification update(UserNotification userNotification) {
        if (existsByUserUuidAndNotificationTypeId(userNotification.getUserUuid(), userNotification.getNotificationTypeId())) {
            return userNotificationRepository.update(userNotification);
        }
        throw new NotFoundException(String.format("user notification not found: %s", userNotification));
    }

    @Override
    public UserNotification findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
        return userNotificationRepository.findByUserUuidAndNotificationTypeId(userUuid, notificationTypeId).orElseThrow(() -> new NotFoundException("user notification not found"));
    }

    @Override
    public boolean existsByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
        return userNotificationRepository.existsByUserUuidAndNotificationTypeId(userUuid, notificationTypeId);
    }
}
