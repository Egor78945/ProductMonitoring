package com.example.authentication_service.service.user;

import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.user.grpc.client.UserGrpcClientService;
import com.example.grpc.user.UserProtoConfiguration;

public abstract class UserProtoService implements UserService<UserProtoConfiguration.UserMessage> {
    protected final UserGrpcClientService userGrpcClientService;

    public UserProtoService(UserGrpcClientService userGrpcClientService) {
        this.userGrpcClientService = userGrpcClientService;
    }

    @Override
    public UserProtoConfiguration.UserMessage getByAccountName(String accountName) {
        return userGrpcClientService.getByAccountName(GrpcMessageBuilder.buildFrom(accountName));
    }
}
