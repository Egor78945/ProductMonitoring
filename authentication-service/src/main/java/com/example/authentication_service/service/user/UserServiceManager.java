package com.example.authentication_service.service.user;

import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.user.grpc.client.UserGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceManager implements UserService {
    private final UserGrpcClientService userGrpcClientService;

    public UserServiceManager(UserGrpcClientService userGrpcClientService) {
        this.userGrpcClientService = userGrpcClientService;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userGrpcClientService.existsUserByEmail(GrpcMessageBuilder.buildFrom(email)).getBoolean();
    }
}
