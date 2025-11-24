package com.example.product_manager_service.configuration.grpc.marketplace

import com.example.grpc.user.MarketplacePathDefinitionProtoServiceGrpc
import com.example.grpc.user.ProductProtoServiceGrpc
import io.grpc.Channel
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcMarketplaceConfiguration() {
    @GrpcClient("user-database-grpc-service")
    private lateinit var channel: Channel

    @Bean
    fun marketplaceProtoServiceBlockingStub(): MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceBlockingStub =
        MarketplacePathDefinitionProtoServiceGrpc.newBlockingStub(channel)

    @Bean
    fun productProtoServiceBlockingStub(): ProductProtoServiceGrpc.ProductProtoServiceBlockingStub =
        ProductProtoServiceGrpc.newBlockingStub(channel)
}