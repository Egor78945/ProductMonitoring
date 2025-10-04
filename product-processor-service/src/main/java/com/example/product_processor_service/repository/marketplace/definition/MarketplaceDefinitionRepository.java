package com.example.product_processor_service.repository.marketplace.definition;

import java.util.List;

public abstract class MarketplaceDefinitionRepository<M> {
    public abstract List<M> findAll();
    public abstract List<String> findBaseUrlByMarketplaceId(long id);
    public abstract boolean isMarketplaceSupported(String marketplaceDomain);
}
