package com.example.user_database_manager_service.repository.user.notification;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import com.example.user_database_manager_service.service.user.notification.mapper.UserNotificationMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public abstract class JooqUserNotificationProtoRepository extends JooqUserNotificationRepository<UserProtoConfiguration.UserNotificationMessage> {
    public JooqUserNotificationProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage save(UserProtoConfiguration.UserNotificationMessage entity) {
        return dslContext
                .insertInto(Tables.USER_NOTIFICATION)
                .set(Tables.USER_NOTIFICATION.USER_UUID, UUID.fromString(entity.getUserUuid()))
                .set(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID, entity.getNotificationTypeId())
                .set(Tables.USER_NOTIFICATION.NOTIFIED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getNotifiedAt()), ZoneId.systemDefault()))
                .returning()
                .fetchOne(UserNotificationMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.UserNotificationMessage update(UserProtoConfiguration.UserNotificationMessage entity) {
        return dslContext
                .update(Tables.USER_NOTIFICATION)
                .set(Tables.USER_NOTIFICATION.USER_UUID, UUID.fromString(entity.getUserUuid()))
                .set(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID, entity.getNotificationTypeId())
                .set(Tables.USER_NOTIFICATION.NOTIFIED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getNotifiedAt()), ZoneId.systemDefault()))
                .returning()
                .fetchOne(UserNotificationMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.UserNotificationMessage> findByUserUuidAndNotificationTypeId(UUID userUuid, Long notificationTypeId) {
        return dslContext
                .selectFrom(Tables.USER_NOTIFICATION)
                .where(Tables.USER_NOTIFICATION.USER_UUID.eq(userUuid)
                        .and(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID.eq(notificationTypeId)))
                .fetchOptional(UserNotificationMapper::mapTo);
    }
}
