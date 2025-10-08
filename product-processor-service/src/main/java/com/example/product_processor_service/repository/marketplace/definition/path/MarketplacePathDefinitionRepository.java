package com.example.product_processor_service.repository.marketplace.definition.path;

public abstract class MarketplacePathDefinitionRepository<T> {
    public abstract boolean existsByPath(String path);
}
