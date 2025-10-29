package com.example.product_processor_service.service.marketplace;

import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;

import java.util.List;

public interface MarketplacePathService {
    boolean isMarketplaceSupported(String marketplaceDomain);
    List<MarketplacePathDefinition> getSupportedMarketplaces();
}
