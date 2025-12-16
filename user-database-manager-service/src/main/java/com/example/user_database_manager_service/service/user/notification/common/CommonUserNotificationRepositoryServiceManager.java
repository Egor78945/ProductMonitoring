package com.example.user_database_manager_service.service.user.notification.common;

import com.example.user_database_manager_service.repository.user.notification.common.CommonUserNotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonUserNotificationRepositoryServiceManager extends CommonUserNotificationRepositoryService{
    public CommonUserNotificationRepositoryServiceManager(CommonUserNotificationRepository commonUserNotificationRepository) {
        super(commonUserNotificationRepository);
    }
}
