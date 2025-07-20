package com.example.authentication_service.service.account.grpc.client;

import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AccountGrpcClientServiceManager extends AccountGrpcClientService {
    public AccountGrpcClientServiceManager(AccountProtoServiceGrpc.AccountProtoServiceBlockingStub stub) {
        super(stub);
    }

    @Override
    public UserProtoConfiguration.AccountMessage getAccountByUUID(UserProtoConfiguration.StringMessage accountUUID) {
        return stub.getAccountByUUID(accountUUID);
    }

    @Override
    public UserProtoConfiguration.AccountMessage getAccountByUserUUID(UserProtoConfiguration.StringMessage userUUID) {
        return stub.getAccountByUserUUID(userUUID);
    }

    @Override
    public UserProtoConfiguration.BooleanMessage registerAccount(UserProtoConfiguration.AccountMessage account) {
        return stub.registerAccount(account);
    }
}
