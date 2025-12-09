package com.example.user_database_manager_service.repository.user.notification;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserNotificationProtoRepositoryManager extends JooqUserNotificationProtoRepository {
    public JooqUserNotificationProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
