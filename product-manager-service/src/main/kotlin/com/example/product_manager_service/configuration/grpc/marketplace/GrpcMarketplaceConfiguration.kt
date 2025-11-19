package com.example.product_manager_service.configuration.grpc.marketplace

import com.example.grpc.user.MarketplacePathDefinitionProtoServiceGrpc
import io.grpc.Channel
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcMarketplaceConfiguration() {
    @GrpcClient("user-database-grpc-service")
    private lateinit var channel: Channel

    @Bean
    fun productProtoServiceBlockingStub(): MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceBlockingStub =
        MarketplacePathDefinitionProtoServiceGrpc.newBlockingStub(channel)
}