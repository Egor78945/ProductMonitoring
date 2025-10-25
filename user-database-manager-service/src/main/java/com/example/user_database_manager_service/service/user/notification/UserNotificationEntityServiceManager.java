package com.example.user_database_manager_service.service.user.notification;

import com.example.user_database_manager_service.repository.user.notification.UserNotificationEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationEntityServiceManager extends UserNotificationEntityService{
    public UserNotificationEntityServiceManager(UserNotificationEntityRepository userNotificationEntityRepository) {
        super(userNotificationEntityRepository);
    }
}
