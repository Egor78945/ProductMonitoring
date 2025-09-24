package com.example.product_manager_service.service.product

import com.example.product_manager_service.service.marketplace.validation.MarketplaceValidationService
import org.springframework.stereotype.Service
import java.net.URI

@Service
class ProductUriServiceManager(private val marketplaceValidationService: MarketplaceValidationService<URI>) : ProductService<URI> {
    override fun save(product: URI) {
        if(marketplaceValidationService.isSupported(product)) {
            
        }
    }
}