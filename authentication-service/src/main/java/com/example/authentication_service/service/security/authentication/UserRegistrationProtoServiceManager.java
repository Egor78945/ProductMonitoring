package com.example.authentication_service.service.security.authentication;

import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationProtoServiceManager extends UserRegistrationProtoService{
    protected UserRegistrationProtoServiceManager(AuthenticationGrpcClientService authenticationGrpcClientService) {
        super(authenticationGrpcClientService);
    }
}
