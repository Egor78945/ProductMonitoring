package com.example.authentication_service.service.user.grpc.client;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.grpc.user.UserProtoServiceGrpc;

public abstract class UserGrpcClientService extends GrpcClientService<UserProtoServiceGrpc.UserProtoServiceBlockingStub> {
    public UserGrpcClientService(UserProtoServiceGrpc.UserProtoServiceBlockingStub stub) {
        super(stub);
    }

    public abstract UserProtoConfiguration.UserMessage getUserByUUID(UserProtoConfiguration.StringMessage uuid);
    public abstract void registerUser(UserProtoConfiguration.UserRegistrationMessage userRegistrationMessage);
    public abstract UserProtoConfiguration.BooleanMessage existsUserByEmail(UserProtoConfiguration.StringMessage email);
}
