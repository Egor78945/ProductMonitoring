package com.example.product_processor_service.repository.user.notification;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserNotificationEntityRepositoryManager extends JooqUserNotificationEntityRepository{
    public JooqUserNotificationEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
