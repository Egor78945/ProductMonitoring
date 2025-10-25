package com.example.authentication_service.service.user.grpc.client;

import com.example.grpc.user.UserProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class UserGrpcClientServiceManager extends UserGrpcClientService {
    public UserGrpcClientServiceManager(UserProtoServiceGrpc.UserProtoServiceBlockingStub stub) {
        super(stub);
    }
}
