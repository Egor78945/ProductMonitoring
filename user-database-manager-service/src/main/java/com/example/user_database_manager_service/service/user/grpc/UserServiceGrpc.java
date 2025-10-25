package com.example.user_database_manager_service.service.user.grpc;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.grpc.user.UserProtoServiceGrpc;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.user.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class UserServiceGrpc extends UserProtoServiceGrpc.UserProtoServiceImplBase {
    private final UserService<UserProtoConfiguration.UserMessage> userService;

    public UserServiceGrpc(UserService<UserProtoConfiguration.UserMessage> userService) {
        this.userService = userService;
    }

    @Override
    public void existsUserByEmail(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcMapper.map(userService.existsByEmail(request.getString())));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void existsUserByUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcMapper.map(userService.existsByUUID(UUID.fromString(request.getString()))));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getUserByUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.UserMessage> responseObserver) {
        try {
            responseObserver.onNext(userService.findByUUID(UUID.fromString(request.getString())));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void save(UserProtoConfiguration.UserMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            userService.save(request);
            responseObserver.onNext(GrpcMapper.map());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getByAccountName(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.UserMessage> responseObserver) {
        try {
            responseObserver.onNext(userService.findByAccountName(request.getString()));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
