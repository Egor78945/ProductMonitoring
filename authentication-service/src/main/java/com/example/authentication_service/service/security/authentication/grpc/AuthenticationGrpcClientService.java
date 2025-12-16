package com.example.authentication_service.service.security.authentication.grpc;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.example.grpc.user.AuthenticationProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;

public abstract class AuthenticationGrpcClientService extends GrpcClientService<AuthenticationProtoServiceGrpc.AuthenticationProtoServiceBlockingStub> {
    public AuthenticationGrpcClientService(AuthenticationProtoServiceGrpc.AuthenticationProtoServiceBlockingStub stub) {
        super(stub);
    }

    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage userRegistrationMessage) {
        return stub.register(userRegistrationMessage);
    }

    public void deleteByEmail(UserProtoConfiguration.StringMessage userEmail) {
        stub.deleteByEmail(userEmail);
    }
}
