package com.example.product_processor_service.repository.user.notification.type;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DSLContext;

public abstract class JooqUserNotificationTypeRepository<UNT> extends UserNotificationTypeRepository<UNT> {
    protected final DSLContext dslContext;

    public JooqUserNotificationTypeRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
