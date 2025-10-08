package com.example.product_manager_service.service.marketplace.validation

interface MarketplaceValidationService<T> {
    fun isSupported(marketplaceSubject: T) : Boolean
}