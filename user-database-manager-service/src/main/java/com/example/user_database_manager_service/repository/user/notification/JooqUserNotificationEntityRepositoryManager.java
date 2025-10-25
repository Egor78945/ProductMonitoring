package com.example.user_database_manager_service.repository.user.notification;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserNotificationEntityRepositoryManager extends JooqUserNotificationEntityRepository{
    public JooqUserNotificationEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
