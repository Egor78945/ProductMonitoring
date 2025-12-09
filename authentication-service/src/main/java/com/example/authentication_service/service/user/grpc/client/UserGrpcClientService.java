package com.example.authentication_service.service.user.grpc.client;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.grpc.user.UserProtoServiceGrpc;

public abstract class UserGrpcClientService extends GrpcClientService<UserProtoServiceGrpc.UserProtoServiceBlockingStub> {
    public UserGrpcClientService(UserProtoServiceGrpc.UserProtoServiceBlockingStub stub) {
        super(stub);
    }

    public UserProtoConfiguration.UserMessage getUserByUUID(UserProtoConfiguration.StringMessage uuid) {
        return stub.getUserByUUID(uuid);
    }

    public UserProtoConfiguration.UserMessage getByAccountName(UserProtoConfiguration.StringMessage accountName) {
        return stub.getByAccountName(accountName);
    }

    public void registerUser(UserProtoConfiguration.UserMessage userMessage) {
        stub.save(userMessage);
    }

    public UserProtoConfiguration.BooleanMessage existsUserByEmail(UserProtoConfiguration.StringMessage email) {
        return stub.existsUserByEmail(email);
    }

    public UserProtoConfiguration.BooleanMessage existsUserByUUID(UserProtoConfiguration.StringMessage uuid) {
        return stub.existsUserByUUID(uuid);
    }
}
