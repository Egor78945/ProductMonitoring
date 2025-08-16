package com.example.product_processor_service.repository.marketplace.definition;

import java.util.List;

public abstract class MarketplaceDefinitionRepository<M> {
    public abstract List<M> findAll();
}
