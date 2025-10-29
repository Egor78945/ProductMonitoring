package com.example.user_database_manager_service.service.user.notification;

import com.example.user_database_manager_service.repository.user.notification.JooqUserNotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationEntityServiceManager extends UserNotificationEntityService{
    public UserNotificationEntityServiceManager(JooqUserNotificationRepository userNotificationEntityRepository) {
        super(userNotificationEntityRepository);
    }
}
