package com.example.product_processor_service.configuration.grpc.client;

import com.example.grpc.user.*;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {
    @GrpcClient("user-database-grpc-service")
    private Channel channel;

    @Bean
    public ProductProtoServiceGrpc.ProductProtoServiceBlockingStub productProtoServiceBlockingStub() {
        return ProductProtoServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceBlockingStub marketplacePathDefinitionProtoServiceBlockingStub() {
        return MarketplacePathDefinitionProtoServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public MarketplaceSelectorProtoServiceGrpc.MarketplaceSelectorProtoServiceBlockingStub marketplaceSelectorProtoServiceBlockingStub() {
        return MarketplaceSelectorProtoServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public MarketplaceDefinitionProtoServiceGrpc.MarketplaceDefinitionProtoServiceBlockingStub marketplaceDefinitionProtoServiceBlockingStub() {
        return MarketplaceDefinitionProtoServiceGrpc.newBlockingStub(channel);
    }
}