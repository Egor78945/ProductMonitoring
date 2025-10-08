package com.example.product_manager_service.service.marketplace.grpc

import com.example.grpc.product.MarketplaceProtoServiceGrpc
import com.example.grpc.product.ProductServiceProtoConfiguration
import com.example.product_manager_service.service.common.grpc.GrpcClientService

abstract class MarketplaceGrpcClientService(stub: MarketplaceProtoServiceGrpc.MarketplaceProtoServiceBlockingStub) :
    GrpcClientService<MarketplaceProtoServiceGrpc.MarketplaceProtoServiceBlockingStub>(stub) {
    abstract fun isMarketplaceSupported(marketplaceDomain: ProductServiceProtoConfiguration.StringMessage): ProductServiceProtoConfiguration.BooleanMessage
}
