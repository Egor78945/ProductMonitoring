package com.example.product_processor_service.service.marketplace.manager.router;

import com.example.product_processor_service.service.marketplace.initializer.MarketplaceInitializer;
import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceManagerRouterServiceManager extends MarketplaceManagerRouterService {
    private final MarketplaceInitializer<String, MarketplaceManagerService> marketplaceInitializer;

    public MarketplaceManagerRouterServiceManager(MarketplaceInitializer<String, MarketplaceManagerService> marketplaceInitializer) {
        this.marketplaceInitializer = marketplaceInitializer;
    }

    @Override
    public MarketplaceManagerService getByBaseUrl(String baseUrl) {
        return marketplaceInitializer.getMarketplaces().get(baseUrl);
    }
}
