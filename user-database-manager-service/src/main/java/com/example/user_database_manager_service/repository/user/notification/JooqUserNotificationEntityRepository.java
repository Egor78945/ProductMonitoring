package com.example.user_database_manager_service.repository.user.notification;

import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqUserNotificationEntityRepository extends JooqUserNotificationRepository {
    public JooqUserNotificationEntityRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserNotification save(UserNotification entity) {
        return dslContext
                .insertInto(Tables.USER_NOTIFICATION)
                .set(Tables.USER_NOTIFICATION.USER_UUID, entity.getUserUuid())
                .set(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID, entity.getNotificationTypeId())
                .set(Tables.USER_NOTIFICATION.NOTIFIED_AT, entity.getNotifiedAt().toLocalDateTime())
                .returning()
                .fetchOneInto(UserNotification.class);
    }
}
