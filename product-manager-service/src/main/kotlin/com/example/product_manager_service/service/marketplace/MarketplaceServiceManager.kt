package com.example.product_manager_service.service.marketplace

import com.example.product_manager_service.service.common.grpc.mapper.GrpcMapperService
import com.example.product_manager_service.service.marketplace.grpc.MarketplaceGrpcClientService
import org.springframework.stereotype.Service

@Service
class MarketplaceServiceManager(private val marketplaceGrpcClientService: MarketplaceGrpcClientService): MarketplaceService {
    private lateinit var supportedMarketplaces: Set<String>

    override fun getSupported(): Set<String> {
        if(!this::supportedMarketplaces.isInitialized) {
            supportedMarketplaces = GrpcMapperService.mapTo(marketplaceGrpcClientService.getSupportedMarketplaces())
        }
        return supportedMarketplaces
    }
}