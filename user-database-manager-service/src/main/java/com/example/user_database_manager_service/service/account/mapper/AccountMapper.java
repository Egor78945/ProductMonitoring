package com.example.user_database_manager_service.service.account.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import nu.studer.sample.tables.Account;
import nu.studer.sample.tables.records.AccountRecord;
import org.jooq.Record1;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class AccountMapper {
    public static UserProtoConfiguration.AccountMessage map(AccountRecord record) {
        return UserProtoConfiguration.AccountMessage
                .newBuilder()
                .setId(record.getId())
                .setUuid(record.getUuid().toString())
                .setUserUuid(record.getUserUuid().toString())
                .setName(record.getName())
                .setStatusId(record.getStatusId())
                .setCreatedAt(record.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .setMain(record.getMain())
                .build();
    }
}
