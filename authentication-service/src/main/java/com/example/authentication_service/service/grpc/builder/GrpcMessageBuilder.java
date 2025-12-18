package com.example.authentication_service.service.grpc.builder;

import com.example.authentication_service.model.account.status.AccountStatusEnumeration;
import com.example.authentication_service.model.user.status.UserStatusEnumeration;
import com.example.grpc.user.UserProtoConfiguration;

import java.time.Instant;
import java.util.List;

public class GrpcMessageBuilder {
    public static UserProtoConfiguration.UserMessage buildFrom(String email, UserStatusEnumeration userStatusEnumeration, List<UserProtoConfiguration.RoleMessage> roleIdList) {
        return UserProtoConfiguration.UserMessage
                .newBuilder()
                .setEmail(email.toLowerCase())
                .setStatusId(userStatusEnumeration.getId())
                .setRegisteredAt(Instant.now().toEpochMilli())
                .addAllUserRoles(roleIdList)
                .build();
    }

    public static UserProtoConfiguration.AccountMessage buildFrom(String name, AccountStatusEnumeration accountStatusEnumeration, boolean main) {
        return UserProtoConfiguration.AccountMessage
                .newBuilder()
                .setName(name)
                .setStatusId(accountStatusEnumeration.getId())
                .setCreatedAt(Instant.now().toEpochMilli())
                .setMain(main)
                .build();
    }

    public static UserProtoConfiguration.StringMessage buildFrom(String string) {
        return UserProtoConfiguration.StringMessage
                .newBuilder()
                .setString(string)
                .build();
    }

    public static UserProtoConfiguration.UserRegistrationMessage buildFrom(UserProtoConfiguration.UserMessage userMessage, UserProtoConfiguration.AccountMessage accountMessage) {
        return UserProtoConfiguration.UserRegistrationMessage
                .newBuilder()
                .setUser(userMessage)
                .setAccount(accountMessage)
                .build();
    }
}
