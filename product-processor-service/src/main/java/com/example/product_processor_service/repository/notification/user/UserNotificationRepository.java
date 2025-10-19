package com.example.product_processor_service.repository.notification.user;

import com.example.product_processor_service.model.notification.user.UserNotification;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class UserNotificationRepository extends JooqRepository<UserNotification> {
    public UserNotificationRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserNotification save(UserNotification userNotification) {
        return dslContext
                .insertInto(Tables.USER_NOTIFICATION)
                .set(Tables.USER_NOTIFICATION.USER_UUID, userNotification.getUserUuid())
                .set(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID, userNotification.getNotificationTypeId())
                .set(Tables.USER_NOTIFICATION.NOTIFIED_AT, userNotification.getNotifiedAt().toLocalDateTime())
                .returning()
                .fetchOneInto(UserNotification.class);
    }

    @Override
    public void saveAll(List<UserNotification> userNotification) {
        for (UserNotification un : userNotification) {
            save(un);
        }
    }

    @Override
    public UserNotification update(UserNotification userNotification) {
        return dslContext
                .update(Tables.USER_NOTIFICATION)
                .set(Tables.USER_NOTIFICATION.USER_UUID, userNotification.getUserUuid())
                .set(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID, userNotification.getNotificationTypeId())
                .set(Tables.USER_NOTIFICATION.NOTIFIED_AT, userNotification.getNotifiedAt().toLocalDateTime())
                .returning()
                .fetchOneInto(UserNotification.class);
    }

    public Optional<UserNotification> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
        return dslContext
                .selectFrom(Tables.USER_NOTIFICATION)
                .where(Tables.USER_NOTIFICATION.USER_UUID.eq(userUuid)
                        .and(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID.eq(notificationTypeId)))
                .fetchOptionalInto(UserNotification.class);
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
}
