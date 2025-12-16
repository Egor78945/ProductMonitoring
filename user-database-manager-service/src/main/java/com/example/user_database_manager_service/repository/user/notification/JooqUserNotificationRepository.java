package com.example.user_database_manager_service.repository.user.notification;

import org.jooq.DSLContext;

public abstract class JooqUserNotificationRepository<UN> extends UserNotificationRepository<UN> {
    protected final DSLContext dslContext;

    public JooqUserNotificationRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
