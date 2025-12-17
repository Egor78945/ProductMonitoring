package com.example.authentication_service.service.role.mapper;

import com.example.authentication_service.model.user.role.UserRoleEnumeration;
import com.example.grpc.user.UserProtoConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static UserProtoConfiguration.RoleMessage mapTo(UserRoleEnumeration role){
        return UserProtoConfiguration.RoleMessage
                .newBuilder()
                .setRoleId(role.getId())
                .setName(role.name())
                .build();
    }

    public static List<UserProtoConfiguration.RoleMessage> mapTo(List<UserRoleEnumeration> role){
        return role.stream().map(RoleMapper::mapTo).collect(Collectors.toList());
    }

    public static List<String> mapToString(List<UserProtoConfiguration.RoleMessage> role){
        return role.stream().map(UserProtoConfiguration.RoleMessage::getName).collect(Collectors.toList());
    }
}
