package com.example.user_database_manager_service.service.user.authentication.grpc;

import com.example.grpc.user.AuthenticationProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.user.authentication.UserSupplyingRegistrationService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Qualifier;

@GrpcService
public class AuthenticationServiceGrpc extends AuthenticationProtoServiceGrpc.AuthenticationProtoServiceImplBase {
    private final UserSupplyingRegistrationService<UserProtoConfiguration.UserRegistrationMessage> userAuthenticationService;

    public AuthenticationServiceGrpc(@Qualifier("userSupplyingRegistrationProtoTransactionalServiceManager") UserSupplyingRegistrationService<UserProtoConfiguration.UserRegistrationMessage> userAuthenticationService) {
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
        userAuthenticationService.rollback(request);
        System.out.println("user deleted");
        responseObserver.onNext(GrpcMapper.mapTo());
        responseObserver.onCompleted();
    }
}
