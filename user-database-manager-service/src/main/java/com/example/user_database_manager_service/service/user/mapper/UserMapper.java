package com.example.user_database_manager_service.service.user.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.Record4;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class UserMapper {
    public static UserProtoConfiguration.UserMessage mapTo(Record4<Long, UUID, String, LocalDateTime> userRecord){
        return UserProtoConfiguration.UserMessage
                .newBuilder()
                .setId(userRecord.get(Tables.USERS.ID))
                .setUuid(userRecord.get(Tables.USERS.UUID).toString())
                .setEmail(userRecord.get(Tables.USERS.EMAIL))
                .setRegisteredAt(userRecord.get(Tables.USERS.REGISTERED_AT).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}
