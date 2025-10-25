package com.example.user_database_manager_service.service.user.authentication.grpc;

import com.example.grpc.user.AuthenticationProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.user.authentication.UserAuthenticationService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AuthenticationServiceGrpc extends AuthenticationProtoServiceGrpc.AuthenticationProtoServiceImplBase {
    private final UserAuthenticationService<UserProtoConfiguration.UserRegistrationMessage> userAuthenticationService;

    public AuthenticationServiceGrpc(UserAuthenticationService<UserProtoConfiguration.UserRegistrationMessage> userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @Override
    public void register(UserProtoConfiguration.UserRegistrationMessage request, StreamObserver<UserProtoConfiguration.UserRegistrationMessage> responseObserver) {
            System.out.println("request handled");
            UserProtoConfiguration.UserRegistrationMessage response = userAuthenticationService.register(request);
            System.out.println("request processed");
        try {
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception ex) {
            responseObserver.onError(ex);
        }
    }

    @Override
    public void delete(UserProtoConfiguration.UserRegistrationMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        System.out.println("deleting user");
        userAuthenticationService.delete(request);
        System.out.println("user deleted");
        responseObserver.onNext(GrpcMapper.map());
        responseObserver.onCompleted();
    }
}
