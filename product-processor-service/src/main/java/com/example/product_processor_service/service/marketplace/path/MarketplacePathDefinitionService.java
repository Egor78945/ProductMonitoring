package com.example.product_processor_service.service.marketplace.path;

import java.util.List;

public interface MarketplacePathDefinitionService<MPD> {
    boolean isMarketplaceSupported(String marketplaceDomain);
    List<MPD> getSupportedMarketplaces();
    List<MPD> getByMarketplaceDefinitionId(long marketplaceDefinitionId);
}
