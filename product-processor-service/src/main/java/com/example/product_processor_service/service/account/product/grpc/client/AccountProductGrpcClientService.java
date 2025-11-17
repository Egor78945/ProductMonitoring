package com.example.product_processor_service.service.account.product.grpc.client;

import com.example.grpc.user.AccountProductProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.common.grpc.client.GrpcClientService;

public abstract class AccountProductGrpcClientService extends GrpcClientService<AccountProductProtoServiceGrpc.AccountProductProtoServiceBlockingStub> {
    public AccountProductGrpcClientService(AccountProductProtoServiceGrpc.AccountProductProtoServiceBlockingStub stub) {
        super(stub);
    }

    public UserProtoConfiguration.AccountProductMessage save(UserProtoConfiguration.AccountProductMessage accountProductMessage) {
        return stub.save(accountProductMessage);
    }
}
