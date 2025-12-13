package com.example.user_database_manager_service.service.account.grpc;

import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class AccountServiceGrpc extends AccountProtoServiceGrpc.AccountProtoServiceImplBase {
    private final AccountService<UserProtoConfiguration.AccountMessage> accountService;

    public AccountServiceGrpc(AccountService<UserProtoConfiguration.AccountMessage> accountService) {
        this.accountService = accountService;
    }

    @Override
    public void save(UserProtoConfiguration.AccountMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            accountService.save(request);
            responseObserver.onNext(GrpcMapper.mapTo());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void existsAccountByUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcMapper.mapTo(accountService.existsByUUID(UUID.fromString(request.getString()))));
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

    @Override
    public void getMainAccountByUserUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.AccountMessage> responseObserver) {
        try {
            responseObserver.onNext(accountService.findMainByUserUuid(UUID.fromString(request.getString())));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getAccountByName(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.AccountMessage> responseObserver) {
        try {
            responseObserver.onNext(accountService.findByAccountName(request.getString()));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void existsAccountByName(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcMapper.mapTo(accountService.existsByName(request.getString())));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
