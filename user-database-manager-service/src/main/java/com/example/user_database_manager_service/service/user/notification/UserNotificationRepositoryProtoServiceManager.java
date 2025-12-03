package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;
import com.example.user_database_manager_service.service.user.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserNotificationRepositoryProtoServiceManager extends UserNotificationRepositoryProtoService{
    public UserNotificationRepositoryProtoServiceManager(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository, UserService<?> userService) {
        super(userNotificationEntityRepository, userService);
    }
}
