package com.example.authentication_service.service.account.grpc.client;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;

public abstract class AccountGrpcClientService extends GrpcClientService<AccountProtoServiceGrpc.AccountProtoServiceBlockingStub> {
    public AccountGrpcClientService(AccountProtoServiceGrpc.AccountProtoServiceBlockingStub stub) {
        super(stub);
    }

    public abstract UserProtoConfiguration.AccountMessage getAccountByUUID(UserProtoConfiguration.StringMessage accountUUID);
    public abstract UserProtoConfiguration.AccountMessage getAccountByUserUUID(UserProtoConfiguration.StringMessage userUUID);
    public abstract void registerAccount(UserProtoConfiguration.AccountMessage account);
}
