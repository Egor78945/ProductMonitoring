package com.example.user_database_manager_service.service.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.notification.UserNotificationRepository;
import com.example.user_database_manager_service.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserNotificationRepositoryProtoTransactionalServiceManager extends UserNotificationRepositoryProtoService {
    public UserNotificationRepositoryProtoTransactionalServiceManager(UserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> userNotificationEntityRepository, UserService<?> userService) {
        super(userNotificationEntityRepository, userService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserNotificationMessage save(UserProtoConfiguration.UserNotificationMessage entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserNotificationMessage update(UserProtoConfiguration.UserNotificationMessage entity) {
        return super.update(entity);
    }
}
