package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserNotificationRepositoryProtoServiceManager extends UserNotificationRepositoryService<UserProtoConfiguration.UserNotificationMessage> {
    public UserNotificationRepositoryProtoServiceManager(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository) {
        super(userNotificationEntityRepository);
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage save(UserProtoConfiguration.UserNotificationMessage entity) {
        if (!existsBy(UUID.fromString(entity.getUserUuid()), entity.getNotificationTypeId())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("UserNotification is already exists by user uuid and NotificationType: %s", entity));
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage update(UserProtoConfiguration.UserNotificationMessage entity) {
        if (existsBy(UUID.fromString(entity.getUserUuid()), entity.getNotificationTypeId())) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("UserNotification is not found by user uuid and NotificationType: %s", entity));
    }
}
