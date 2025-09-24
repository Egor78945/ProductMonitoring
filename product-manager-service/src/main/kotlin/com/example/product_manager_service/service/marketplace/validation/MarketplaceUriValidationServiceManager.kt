package com.example.product_manager_service.service.marketplace.validation

import org.springframework.stereotype.Service
import java.net.URI

@Service
class MarketplaceUriValidationServiceManager: MarketplaceValidationService<URI> {
    override fun isSupported(marketplaceSubject: URI): Boolean {
        TODO("Not yet implemented")
    }
}