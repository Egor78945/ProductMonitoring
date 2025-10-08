package com.example.product_processor_service.repository.marketplace.definition.path;

import java.util.List;

public abstract class MarketplacePathDefinitionRepository<T> {
    public abstract boolean existsByPath(String path);
    public abstract List<String> getAllPaths();
}
