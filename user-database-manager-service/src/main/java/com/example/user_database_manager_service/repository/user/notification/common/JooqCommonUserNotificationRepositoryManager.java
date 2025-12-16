package com.example.user_database_manager_service.repository.user.notification.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonUserNotificationRepositoryManager extends JooqCommonUserNotificationRepository{
    public JooqCommonUserNotificationRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
