package com.example.product_processor_service.repository.user.notification.type;

import com.example.product_processor_service.model.notification.user.type.entity.UserNotificationType;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

public abstract class JooqUserNotificationTypeEntityRepository extends JooqUserNotificationTypeRepository<UserNotificationType> {
    public JooqUserNotificationTypeEntityRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserNotificationType save(UserNotificationType userNotificationType) {
        return dslContext
                .insertInto(Tables.USER_NOTIFICATION_TYPE)
                .set(Tables.USER_NOTIFICATION_TYPE.NAME, userNotificationType.getName())
                .returning()
                .fetchOneInto(UserNotificationType.class);
    }

    @Override
    public UserNotificationType update(UserNotificationType userNotificationType) {
        return dslContext
                .update(Tables.USER_NOTIFICATION_TYPE)
                .set(Tables.USER_NOTIFICATION_TYPE.NAME, userNotificationType.getName())
                .returning()
                .fetchOneInto(UserNotificationType.class);
    }
}
