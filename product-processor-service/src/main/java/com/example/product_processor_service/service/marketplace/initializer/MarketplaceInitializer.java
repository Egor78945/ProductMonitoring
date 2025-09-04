package com.example.product_processor_service.service.marketplace.initializer;

import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;

import java.util.Map;

public interface MarketplaceInitializer <K, V extends MarketplaceManagerService> {
    Map<K, V> getMarketplaces();
}
