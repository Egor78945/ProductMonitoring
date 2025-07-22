package com.example.user_database_manager_service.service.user.grpc;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.grpc.user.UserProtoServiceGrpc;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.authentication.UserAuthenticationService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class UserServiceGrpc extends UserProtoServiceGrpc.UserProtoServiceImplBase {
    private final UserAuthenticationService<UserProtoConfiguration.UserRegistrationMessage> authenticationService;
    private final UserService userService;

    public UserServiceGrpc(UserAuthenticationService<UserProtoConfiguration.UserRegistrationMessage> authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public void existsUserByEmail(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            System.out.println("HEREEEEEEEE");
            responseObserver.onNext(UserProtoConfiguration.BooleanMessage
                    .newBuilder()
                    .setBoolean(userService.existsByEmail(request.getString()))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void existsUserByUUID(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(UserProtoConfiguration.BooleanMessage
                    .newBuilder()
                    .setBoolean(userService.existsByUUID(UUID.fromString(request.getString())))
                    .build());
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
    public void registerUser(UserProtoConfiguration.UserRegistrationMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
            System.out.println("REGISTEEEEEER");
            authenticationService.register(request);
            responseObserver.onNext(UserProtoConfiguration.EmptyMessage
                    .newBuilder()
                    .build());
            responseObserver.onCompleted();
    }
}
