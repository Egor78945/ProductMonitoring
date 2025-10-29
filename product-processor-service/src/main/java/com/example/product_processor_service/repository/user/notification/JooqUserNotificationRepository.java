package com.example.product_processor_service.repository.user.notification;

import com.example.product_processor_service.util.function.Scrypt;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqUserNotificationRepository<UN> extends UserNotificationRepository<UN> {
    protected final DSLContext dslContext;

    public JooqUserNotificationRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean existsByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.USER_NOTIFICATION)
                                .where(Tables.USER_NOTIFICATION.USER_UUID.eq(userUuid)
                                        .and(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID.eq(notificationTypeId)))
                );
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
