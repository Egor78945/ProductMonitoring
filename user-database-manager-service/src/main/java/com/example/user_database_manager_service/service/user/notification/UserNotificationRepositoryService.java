package com.example.user_database_manager_service.service.user.notification;

import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class UserNotificationRepositoryService<UN> implements UserNotificationService<UN> {
    protected final UserNotificationRepository<UN> userNotificationEntityRepository;

    public UserNotificationRepositoryService(UserNotificationRepository<UN> userNotificationEntityRepository) {
        this.userNotificationEntityRepository = userNotificationEntityRepository;
    }

    @Override
    public UN save(UN entity) {
        return userNotificationEntityRepository.save(entity);
    }

    @Override
    public UN update(UN entity) {
        return userNotificationEntityRepository.update(entity);
    }

    @Override
    public Optional<UN> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
        return userNotificationEntityRepository.findByUserUuidAndNotificationTypeId(userUuid, notificationTypeId);
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
