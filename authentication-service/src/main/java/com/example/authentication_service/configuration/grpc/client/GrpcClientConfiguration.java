package com.example.authentication_service.configuration.grpc.client;

import com.example.grpc.user.AccountProtoServiceGrpc;
import com.example.grpc.user.UserProtoServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {
    @Bean
    public UserProtoServiceGrpc.UserProtoServiceBlockingStub userServiceBlockingStub(@GrpcClient("user-database-grpc-service") UserProtoServiceGrpc.UserProtoServiceBlockingStub stub){
        return stub;
    }

    @Bean
    public AccountProtoServiceGrpc.AccountProtoServiceBlockingStub userServiceBlockingStub(@GrpcClient("user-database-grpc-service") AccountProtoServiceGrpc.AccountProtoServiceBlockingStub stub){
        return stub;
    }
}
