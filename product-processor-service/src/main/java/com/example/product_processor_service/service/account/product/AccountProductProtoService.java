package com.example.product_processor_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.account.product.grpc.client.AccountProductGrpcClientService;

public abstract class AccountProductProtoService implements AccountProductService<UserProtoConfiguration.AccountProductMessage> {
    protected final AccountProductGrpcClientService accountProductGrpcClientService;

    public AccountProductProtoService(AccountProductGrpcClientService accountProductGrpcClientService) {
        this.accountProductGrpcClientService = accountProductGrpcClientService;
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage save(UserProtoConfiguration.AccountProductMessage entity) {
        return accountProductGrpcClientService.save(entity);
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage update(UserProtoConfiguration.AccountProductMessage entity) {
        return null;
    }

    @Override
    public void delete(UserProtoConfiguration.AccountProductMessage entity) {
        //TODO
    }
}
