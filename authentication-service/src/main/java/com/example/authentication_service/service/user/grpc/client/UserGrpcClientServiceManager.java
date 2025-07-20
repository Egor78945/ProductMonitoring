package com.example.authentication_service.service.user.grpc.client;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.grpc.user.UserProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class UserGrpcClientServiceManager extends UserGrpcClientService {
    public UserGrpcClientServiceManager(UserProtoServiceGrpc.UserProtoServiceBlockingStub stub) {
        super(stub);
    }

    @Override
    public UserProtoConfiguration.UserMessage getUserByUUID(UserProtoConfiguration.StringMessage uuid) {
        return stub.getUserByUUID(uuid);
    }

    @Override
    public UserProtoConfiguration.BooleanMessage registerUser(UserProtoConfiguration.UserRegistrationMessage userRegistrationMessage) {
        return stub.registerUser(userRegistrationMessage);
    }

    @Override
    public UserProtoConfiguration.BooleanMessage existsUserByEmail(UserProtoConfiguration.StringMessage email) {
        return stub.existsUserByEmail(email);
    }


}
