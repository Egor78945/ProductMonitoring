package com.example.product_manager_service.configuration.grpc.marketplace

import com.example.grpc.product.MarketplaceProtoServiceGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcMarketplaceConfiguration {
    @Bean
    fun productProtoServiceBlockingStub(@GrpcClient("product-grpc-service") productProtoServiceBlockingStub: MarketplaceProtoServiceGrpc.MarketplaceProtoServiceBlockingStub): MarketplaceProtoServiceGrpc.MarketplaceProtoServiceBlockingStub {
        return productProtoServiceBlockingStub
    }
}
