package com.example.user_database_manager_service.service.user.role.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.Record3;

import java.util.UUID;

public class UserRoleMapper {
    public static UserProtoConfiguration.UserRoleMessage mapTo(Record3<Long, UUID, Long> userRoleRecord) {
        return UserProtoConfiguration.UserRoleMessage
                .newBuilder()
                .setUserUUID(userRoleRecord.get(Tables.USERS_ROLES.USER_UUID).toString())
                .setRoleId(userRoleRecord.get(Tables.USERS_ROLES.ROLE_ID))
                .build();
    }
}
