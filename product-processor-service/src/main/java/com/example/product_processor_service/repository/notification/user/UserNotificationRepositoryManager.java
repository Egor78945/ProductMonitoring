package com.example.product_processor_service.repository.notification.user;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserNotificationRepositoryManager extends UserNotificationRepository{
    public UserNotificationRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
