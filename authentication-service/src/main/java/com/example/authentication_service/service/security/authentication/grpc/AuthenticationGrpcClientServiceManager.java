package com.example.authentication_service.service.security.authentication.grpc;

import com.example.grpc.user.AuthenticationProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationGrpcClientServiceManager extends AuthenticationGrpcClientService{
    public AuthenticationGrpcClientServiceManager(AuthenticationProtoServiceGrpc.AuthenticationProtoServiceBlockingStub stub) {
        super(stub);
    }
}
