package com.example.product_processor_service.repository.marketplace.selector;

import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;
import org.jooq.DSLContext;

import java.util.Optional;

public abstract class MarketplaceSelectorRepository {
    protected final DSLContext dslContext;

    public MarketplaceSelectorRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public abstract Optional<MarketplaceSelector> findByMarketplaceId(long marketplaceId);
    public abstract Optional<MarketplaceSelector> findByMarketplaceName(String name);
}
