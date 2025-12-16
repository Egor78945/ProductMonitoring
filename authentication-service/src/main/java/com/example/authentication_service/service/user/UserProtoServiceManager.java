package com.example.authentication_service.service.user;

import com.example.authentication_service.service.user.grpc.client.UserGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class UserProtoServiceManager extends UserProtoService{
    public UserProtoServiceManager(UserGrpcClientService userGrpcClientService) {
        super(userGrpcClientService);
    }
}
