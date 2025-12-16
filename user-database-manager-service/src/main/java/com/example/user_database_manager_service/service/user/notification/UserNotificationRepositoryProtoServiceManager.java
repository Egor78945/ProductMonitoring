package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.notification.common.CommonUserNotificationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserNotificationRepositoryProtoServiceManager extends UserNotificationRepositoryProtoService {
    public UserNotificationRepositoryProtoServiceManager(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository, CommonUserService commonUserService, CommonUserNotificationService commonUserNotificationService) {
        super(userNotificationEntityRepository, commonUserService, commonUserNotificationService);
    }
}
