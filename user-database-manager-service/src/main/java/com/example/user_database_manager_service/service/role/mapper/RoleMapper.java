package com.example.user_database_manager_service.service.role.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.UserRoleRecord;

public class RoleMapper {
    public static UserProtoConfiguration.RoleMessage mapTo(UserRoleRecord userRoleRecord) {
        return UserProtoConfiguration.RoleMessage
                .newBuilder()
                .setRoleId(userRoleRecord.getId())
                .setName(userRoleRecord.getName())
                .build();
    }
}
