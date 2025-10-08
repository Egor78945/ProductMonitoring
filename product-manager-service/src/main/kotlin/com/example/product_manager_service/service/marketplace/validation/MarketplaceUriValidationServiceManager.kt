package com.example.product_manager_service.service.marketplace.validation

import com.example.product_manager_service.service.common.grpc.mapper.GrpcMapperService
import com.example.product_manager_service.service.marketplace.grpc.MarketplaceGrpcClientService
import org.springframework.stereotype.Service
import java.net.URI

@Service
class MarketplaceUriValidationServiceManager(private val marketplaceGrpcClientService: MarketplaceGrpcClientService): MarketplaceValidationService<URI> {
    override fun isSupported(marketplaceSubject: URI): Boolean {
        return marketplaceGrpcClientService.isMarketplaceSupported(GrpcMapperService.mapTo("${marketplaceSubject.scheme}://${marketplaceSubject.authority}")).boolean
    }
}