package com.example.user_database_manager_service.service.user.notification.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.UserNotificationRecord;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UserNotificationMapper {
    public static UserProtoConfiguration.UserNotificationMessage mapTo(UserNotificationRecord userNotificationRecord) {
        return UserProtoConfiguration.UserNotificationMessage
                .newBuilder()
                .setUserUuid(userNotificationRecord.getUserUuid().toString())
                .setNotificationTypeId(userNotificationRecord.getNotificationTypeId())
                .setNotifiedAt(ZonedDateTime.of(userNotificationRecord.getNotifiedAt(), ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}
