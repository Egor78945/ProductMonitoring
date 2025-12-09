package com.example.product_processor_service.service.marketplace.selector;

public interface MarketplaceSelectorService<S> {
    S getByMarketplaceId(long marketplaceId);
    S getByMarketplaceName(String name);
}
