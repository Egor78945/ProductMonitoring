package com.example.product_processor_service.service.marketplace.selector;

import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;

public interface MarketplaceSelectorService<S extends MarketplaceSelector> {
    S getByMarketplaceId(long marketplaceId);
    S getByMarketplaceName(String name);
}
