package com.example.user_database_manager_service.repository.user.notification.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqCommonUserNotificationRepository implements CommonUserNotificationRepository {
    protected final DSLContext dslContext;

    public JooqCommonUserNotificationRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        dslContext
                .deleteFrom(Tables.USER_NOTIFICATION)
                .where(Tables.USER_NOTIFICATION.USER_UUID.eq(userUuid))
                .execute();
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        dslContext
                .deleteFrom(Tables.USER_NOTIFICATION)
                .where(Tables.USER_NOTIFICATION.USER_UUID.in(
                        dslContext
                                .select(Tables.USERS.UUID)
                                .from(Tables.USERS)
                                .where(Tables.USERS.EMAIL.eq(email))
                ))
                .execute();
    }

    @Override
    public void deleteAllByNotificationTypeId(Long notificationTypeId) {
        dslContext
                .deleteFrom(Tables.USER_NOTIFICATION)
                .where(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID.eq(notificationTypeId))
                .execute();
    }

    @Override
    public boolean existsBy(UUID userUuid, Long notificationTypeId) {
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
    public boolean existsByUserUuid(UUID userUuid) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.USER_NOTIFICATION)
                                .where(Tables.USER_NOTIFICATION.USER_UUID.eq(userUuid))
                );
    }

    @Override
    public boolean existsByNotificationTypeId(Long notificationTypeId) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.USER_NOTIFICATION)
                                .where(Tables.USER_NOTIFICATION.NOTIFICATION_TYPE_ID.eq(notificationTypeId))
                );
    }

}
