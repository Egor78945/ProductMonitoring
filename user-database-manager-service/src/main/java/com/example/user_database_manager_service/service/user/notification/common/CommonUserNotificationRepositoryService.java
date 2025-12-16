package com.example.user_database_manager_service.service.user.notification.common;

import com.example.user_database_manager_service.repository.user.notification.common.CommonUserNotificationRepository;

import java.util.UUID;

public abstract class CommonUserNotificationRepositoryService implements CommonUserNotificationService {
    protected final CommonUserNotificationRepository commonUserNotificationRepository;

    public CommonUserNotificationRepositoryService(CommonUserNotificationRepository commonUserNotificationRepository) {
        this.commonUserNotificationRepository = commonUserNotificationRepository;
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        commonUserNotificationRepository.deleteAllByUserUuid(userUuid);
    }

    @Override
    public void deleteAllByNotificationTypeId(Long notificationTypeId) {
        commonUserNotificationRepository.deleteAllByNotificationTypeId(notificationTypeId);
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        commonUserNotificationRepository.deleteAllByUserEmail(email);
    }

    @Override
    public boolean existsBy(UUID userUuid, Long notificationTypeId) {
        return commonUserNotificationRepository.existsBy(userUuid, notificationTypeId);
    }

    @Override
    public boolean existsByUserUuid(UUID userUuid) {
        return commonUserNotificationRepository.existsByUserUuid(userUuid);
    }

    @Override
    public boolean existsByNotificationTypeId(Long notificationTypeId) {
        return commonUserNotificationRepository.existsByNotificationTypeId(notificationTypeId);
    }
}
