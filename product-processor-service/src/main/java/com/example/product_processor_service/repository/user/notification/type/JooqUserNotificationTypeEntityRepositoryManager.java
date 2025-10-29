package com.example.product_processor_service.repository.user.notification.type;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserNotificationTypeEntityRepositoryManager extends JooqUserNotificationTypeEntityRepository{
    public JooqUserNotificationTypeEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
