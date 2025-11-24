package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserNotificationProtoServiceManager extends UserNotificationProtoService {
    public UserNotificationProtoServiceManager(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository) {
        super(userNotificationEntityRepository);
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage save(UserProtoConfiguration.UserNotificationMessage entity) {
        if (!existsBy(UUID.fromString(entity.getUserUuid()), entity.getNotificationTypeId())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage update(UserProtoConfiguration.UserNotificationMessage entity) {
        if (existsBy(UUID.fromString(entity.getUserUuid()), entity.getNotificationTypeId())) {
            return super.update(entity);
        }
        throw new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}
