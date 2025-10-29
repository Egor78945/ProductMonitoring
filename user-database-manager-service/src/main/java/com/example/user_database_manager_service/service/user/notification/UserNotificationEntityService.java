package com.example.user_database_manager_service.service.user.notification;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import com.example.user_database_manager_service.repository.user.notification.JooqUserNotificationRepository;

import java.util.UUID;

public abstract class UserNotificationEntityService implements UserNotificationService<UserNotification> {
    protected final JooqUserNotificationRepository userNotificationEntityRepository;

    public UserNotificationEntityService(JooqUserNotificationRepository userNotificationEntityRepository) {
        this.userNotificationEntityRepository = userNotificationEntityRepository;
    }

    @Override
    public UserNotification save(UserNotification entity) {
        if (!existsBy(entity.getUserUuid(), entity.getNotificationTypeId())) {
            return userNotificationEntityRepository.save(entity);
        } else throw new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage());
    }

    @Override
    public void delete(UserNotification entity) {
        userNotificationEntityRepository.delete(entity);
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        userNotificationEntityRepository.deleteAllByUserUuid(userUuid);
    }

    @Override
    public void deleteAllByNotificationTypeId(Long notificationTypeId) {
        userNotificationEntityRepository.deleteAllByNotificationTypeId(notificationTypeId);
    }

    @Override
    public boolean existsBy(UUID userUuid, Long notificationTypeId) {
        return userNotificationEntityRepository.existsBy(userUuid, notificationTypeId);
    }

    @Override
    public boolean existsByUserUuid(UUID userUuid) {
        return userNotificationEntityRepository.existsByUserUuid(userUuid);
    }

    @Override
    public boolean existsByNotificationTypeId(Long notificationTypeId) {
        return userNotificationEntityRepository.existsByNotificationTypeId(notificationTypeId);
    }
}
