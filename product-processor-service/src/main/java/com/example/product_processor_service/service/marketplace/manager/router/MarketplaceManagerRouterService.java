package com.example.product_processor_service.service.marketplace.manager.router;

import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;

public abstract class MarketplaceManagerRouterService<T> {
    public abstract MarketplaceManagerService<T> getByBaseUrl(String baseUrl);
}
