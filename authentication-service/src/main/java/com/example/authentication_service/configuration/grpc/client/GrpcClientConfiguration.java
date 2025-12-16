package com.example.authentication_service.configuration.grpc.client;

import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.AuthenticationProtoServiceGrpc;
import com.example.grpc.user.UserProtoServiceGrpc;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {
    @GrpcClient("user-database-grpc-service")
    private Channel channel;

    @Bean
    public UserProtoServiceGrpc.UserProtoServiceBlockingStub userServiceBlockingStub() {
        return UserProtoServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public AccountProtoServiceGrpc.AccountProtoServiceBlockingStub accountServiceBlockingStub() {
        return AccountProtoServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public AuthenticationProtoServiceGrpc.AuthenticationProtoServiceBlockingStub authenticationServiceBlockingStub() {
        return AuthenticationProtoServiceGrpc.newBlockingStub(channel);
    }
}
