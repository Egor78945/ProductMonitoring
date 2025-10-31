package com.example.user_database_manager_service.repository.user.notification;

import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

public abstract class JooqUserNotificationEntityRepository extends JooqUserNotificationRepository<UserNotification> {
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

    @Override
    public void delete(UserNotification entity) {
        dslContext
                .deleteFrom(Tables.USER_NOTIFICATION)
                .where(Tables.USER_NOTIFICATION.USER_UUID.eq(entity.getUserUuid())
                        .and(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID.eq(entity.getNotificationTypeId()))
                        .and(Tables.USER_NOTIFICATION.NOTIFIED_AT.eq(entity.getNotifiedAt().toLocalDateTime())))
                .execute();
    }

    @Override
    public UserNotification update(UserNotification entity) {
        return dslContext
                .update(Tables.USER_NOTIFICATION)
                .set(Tables.USER_NOTIFICATION.USER_UUID, entity.getUserUuid())
                .set(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID, entity.getNotificationTypeId())
                .set(Tables.USER_NOTIFICATION.NOTIFIED_AT, entity.getNotifiedAt().toLocalDateTime())
                .returning()
                .fetchOneInto(UserNotification.class);
    }
}
