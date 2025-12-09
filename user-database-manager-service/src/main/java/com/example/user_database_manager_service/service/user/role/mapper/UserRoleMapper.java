package com.example.user_database_manager_service.service.user.role.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import nu.studer.sample.tables.records.UsersRolesRecord;

import java.util.UUID;

public class UserRoleMapper {
    public static UserProtoConfiguration.UserRoleMessage map(UsersRolesRecord userRoleRecord) {
        return UserProtoConfiguration.UserRoleMessage
                .newBuilder()
                .setUserUUID(userRoleRecord.get(Tables.USERS_ROLES.USER_UUID).toString())
                .setRoleId(userRoleRecord.get(Tables.USERS_ROLES.ROLE_ID))
                .build();
    }

    public static UserProtoConfiguration.UserRoleMessage map(Long roleId, UUID userUuid) {
        return UserProtoConfiguration.UserRoleMessage
                .newBuilder()
                .setRoleId(roleId)
                .setUserUUID(userUuid.toString())
                .build();
    }
}
