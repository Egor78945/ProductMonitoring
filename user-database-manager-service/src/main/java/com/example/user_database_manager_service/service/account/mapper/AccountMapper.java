package com.example.user_database_manager_service.service.account.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.Record5;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class AccountMapper {
    public static UserProtoConfiguration.AccountMessage mapTo(Record5<Long, UUID, UUID, Long, LocalDateTime> accountRecord) {
        return UserProtoConfiguration.AccountMessage
                .newBuilder()
                .setId(accountRecord.get(Tables.ACCOUNT.ID))
                .setUuid(accountRecord.get(Tables.ACCOUNT.UUID).toString())
                .setUserUuid(accountRecord.get(Tables.ACCOUNT.USER_UUID).toString())
                .setStatusId(accountRecord.get(Tables.ACCOUNT.STATUS_ID))
                .setCreatedAt(accountRecord.get(Tables.ACCOUNT.CREATED_AT).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}
