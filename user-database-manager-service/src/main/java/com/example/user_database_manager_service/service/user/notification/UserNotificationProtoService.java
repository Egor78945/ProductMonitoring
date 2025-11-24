package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class UserNotificationProtoService implements UserNotificationService<UserProtoConfiguration.UserNotificationMessage> {
    protected final UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository;

    public UserNotificationProtoService(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository) {
        this.userNotificationEntityRepository = userNotificationEntityRepository;
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage save(UserProtoConfiguration.UserNotificationMessage entity) {
        return userNotificationEntityRepository.save(entity);
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage update(UserProtoConfiguration.UserNotificationMessage entity) {
        return userNotificationEntityRepository.update(entity);
    }

    @Override
    public Optional<UserProtoConfiguration.UserNotificationMessage> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
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
