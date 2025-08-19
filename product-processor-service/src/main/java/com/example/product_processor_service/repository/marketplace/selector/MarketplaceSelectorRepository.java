package com.example.product_processor_service.repository.marketplace.selector;

import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;

import java.util.Optional;

public abstract class MarketplaceSelectorRepository<S extends MarketplaceSelector> {
    public abstract Optional<S> findByMarketplaceId(long marketplaceId);
    public abstract Optional<S> findByMarketplaceName(String name);
}
