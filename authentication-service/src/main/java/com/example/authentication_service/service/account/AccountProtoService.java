package com.example.authentication_service.service.account;

import com.example.authentication_service.service.account.grpc.client.AccountGrpcClientService;
import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.grpc.user.UserProtoConfiguration;

import java.util.UUID;

public abstract class AccountProtoService implements AccountService<UserProtoConfiguration.AccountMessage> {
    protected final AccountGrpcClientService accountGrpcClientService;

    public AccountProtoService(AccountGrpcClientService accountGrpcClientService) {
        this.accountGrpcClientService = accountGrpcClientService;
    }

    @Override
    public UserProtoConfiguration.AccountMessage getByUserUuid(UUID userUuid) {
        return accountGrpcClientService.getAccountByUserUUID(GrpcMessageBuilder.buildFrom(userUuid.toString()));
    }

    @Override
    public UserProtoConfiguration.AccountMessage getMainByUserUuid(UUID userUuid) {
        return accountGrpcClientService.getMainAccountByUserUUID(GrpcMessageBuilder.buildFrom(userUuid.toString()));
    }

    @Override
    public UserProtoConfiguration.AccountMessage getMainByUserEmail(String userEmail) {
        return accountGrpcClientService.getMainAccountByUserEmail(GrpcMessageBuilder.buildFrom(userEmail));
    }

    @Override
    public UserProtoConfiguration.AccountMessage getByName(String name) {
        return accountGrpcClientService.getAccountByName(GrpcMessageBuilder.buildFrom(name));
    }
}
