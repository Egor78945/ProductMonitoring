package com.example.user_database_manager_service.service.account.product.grpc;

import com.example.grpc.user.AccountProductProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AccountProductServiceGrpc extends AccountProductProtoServiceGrpc.AccountProductProtoServiceImplBase {
    private final AccountProductService<UserProtoConfiguration.AccountProductMessage> accountProductService;

    public AccountProductServiceGrpc(AccountProductService<UserProtoConfiguration.AccountProductMessage> accountProductService) {
        this.accountProductService = accountProductService;
    }

    @Override
    public void save(UserProtoConfiguration.AccountProductMessage request, StreamObserver<UserProtoConfiguration.AccountProductMessage> responseObserver) {
        try {
            responseObserver.onNext(accountProductService.save(request));
            responseObserver.onCompleted();
        } catch (AlreadyExistsException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteByAccountUuidAndProductUri(UserProtoConfiguration.StringStringMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {

    }
}
