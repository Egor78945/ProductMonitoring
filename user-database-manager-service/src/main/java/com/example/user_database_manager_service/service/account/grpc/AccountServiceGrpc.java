package com.example.user_database_manager_service.service.account.grpc;

import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.grpc.builder.GrpcItemBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class AccountServiceGrpc extends AccountProtoServiceGrpc.AccountProtoServiceImplBase {
    private final AccountService accountService;

    public AccountServiceGrpc(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void registerAccount(UserProtoConfiguration.AccountMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            accountService.save(request);
            responseObserver.onNext(GrpcItemBuilder.buildEmpty());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void existsAccountByUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcItemBuilder.buildFrom(accountService.existsByUUID(UUID.fromString(request.getString()))));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getAccountByUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.AccountMessage> responseObserver) {
        try {
            responseObserver.onNext(accountService.findByUUID(UUID.fromString(request.getString())));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getAccountByUserUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.AccountMessage> responseObserver) {
        try {
            responseObserver.onNext(accountService.findByUserUUID(UUID.fromString(request.getString())));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
