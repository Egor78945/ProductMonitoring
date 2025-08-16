package com.example.product_processor_service.service.marketplace.router;

import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;

public abstract class MarketplaceManagerRouterService {
    public abstract MarketplaceManagerService getByBaseUrl(String baseUrl);
}
