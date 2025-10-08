package com.example.product_manager_service.service.marketplace.grpc

import com.example.grpc.product.MarketplaceProtoServiceGrpc
import com.example.grpc.product.ProductServiceProtoConfiguration
import com.example.product_manager_service.exception.GrpcHandlerException
import org.springframework.stereotype.Service

@Service
class MarketplaceGrpcClientServiceManager(stub: MarketplaceProtoServiceGrpc.MarketplaceProtoServiceBlockingStub) :
    MarketplaceGrpcClientService(stub) {
    override fun isMarketplaceSupported(marketplaceDomain: ProductServiceProtoConfiguration.StringMessage): ProductServiceProtoConfiguration.BooleanMessage {
        try {
            return stub?.isMarketplaceSupports(marketplaceDomain)?:throw GrpcHandlerException("message is null")
        } catch (e: Exception) {
            throw GrpcHandlerException(e)
        }
    }
}
