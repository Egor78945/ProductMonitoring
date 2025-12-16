package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.notification.common.CommonUserNotificationService;

import java.util.UUID;

public abstract class UserNotificationRepositoryProtoService extends UserNotificationRepositoryService<UserProtoConfiguration.UserNotificationMessage> {
    protected final CommonUserService commonUserService;
    protected final CommonUserNotificationService commonUserNotificationService;

    public UserNotificationRepositoryProtoService(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository, CommonUserService commonUserService, CommonUserNotificationService commonUserNotificationService) {
        super(userNotificationEntityRepository);
        this.commonUserService = commonUserService;
        this.commonUserNotificationService = commonUserNotificationService;
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage save(UserProtoConfiguration.UserNotificationMessage entity) {
        if (commonUserService.existsByUUID(UUID.fromString(entity.getUserUuid())) && !commonUserNotificationService.existsBy(UUID.fromString(entity.getUserUuid()), entity.getNotificationTypeId())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("UserNotification is already exists by user uuid and NotificationType: %s", entity));
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage update(UserProtoConfiguration.UserNotificationMessage entity) {
        if (commonUserNotificationService.existsBy(UUID.fromString(entity.getUserUuid()), entity.getNotificationTypeId())) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("UserNotification is not found by user uuid and NotificationType: %s", entity));
    }
}
