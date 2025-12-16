package com.example.authentication_service.service.account.grpc.client;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;

public abstract class AccountGrpcClientService extends GrpcClientService<AccountProtoServiceGrpc.AccountProtoServiceBlockingStub> {
    public AccountGrpcClientService(AccountProtoServiceGrpc.AccountProtoServiceBlockingStub stub) {
        super(stub);
    }

    public void registerAccount(UserProtoConfiguration.AccountMessage account) {
        stub.save(account);
    }

    public UserProtoConfiguration.AccountMessage getAccountByUUID(UserProtoConfiguration.StringMessage accountUUID) {
        return stub.getAccountByUUID(accountUUID);
    }

    public UserProtoConfiguration.AccountMessage getAccountByUserUUID(UserProtoConfiguration.StringMessage userUUID) {
        return stub.getAccountByUserUUID(userUUID);
    }

    public UserProtoConfiguration.AccountMessage getAccountByName(UserProtoConfiguration.StringMessage accountName) {
        return stub.getAccountByName(accountName);
    }

    public UserProtoConfiguration.AccountMessage getMainAccountByUserUUID(UserProtoConfiguration.StringMessage userUUID) {
        return stub.getMainAccountByUserUUID(userUUID);
    }

    public UserProtoConfiguration.AccountMessage getMainAccountByUserEmail(UserProtoConfiguration.StringMessage userEmail) {
        return stub.getMainAccountByUserEmail(userEmail);
    }

    public UserProtoConfiguration.BooleanMessage existsAccountByUUID(UserProtoConfiguration.StringMessage accountUUID) {
        return stub.existsAccountByUUID(accountUUID);
    }

    public UserProtoConfiguration.BooleanMessage existsAccountByName(UserProtoConfiguration.StringMessage accountName) {
        return stub.existsAccountByName(accountName);
    }
}
