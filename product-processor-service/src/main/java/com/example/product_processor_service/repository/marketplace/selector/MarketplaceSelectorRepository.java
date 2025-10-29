package com.example.product_processor_service.repository.marketplace.selector;

import com.example.product_processor_service.repository.Repository;

import java.util.Optional;

public abstract class MarketplaceSelectorRepository<MS> extends Repository<MS> {
    public abstract Optional<MS> findByMarketplaceId(long marketplaceId);

    public abstract Optional<MS> findByMarketplaceName(String name);
}
