package com.example.product_processor_service.service.marketplace;

import java.util.List;

public interface MarketplaceService {
    boolean isMarketplaceSupported(String marketplaceDomain);
    List<String> getSupportedMarketplaces();
}
