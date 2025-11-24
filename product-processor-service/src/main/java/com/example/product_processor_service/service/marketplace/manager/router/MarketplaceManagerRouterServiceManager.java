package com.example.product_processor_service.service.marketplace.manager.router;

import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.service.marketplace.initializer.MarketplaceInitializer;
import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceManagerRouterServiceManager extends MarketplaceManagerRouterService<ProductDTO> {
    private final MarketplaceInitializer<String, ProductDTO, MarketplaceManagerService<ProductDTO>> marketplaceInitializer;

    public MarketplaceManagerRouterServiceManager(MarketplaceInitializer<String, ProductDTO, MarketplaceManagerService<ProductDTO>> marketplaceInitializer) {
        this.marketplaceInitializer = marketplaceInitializer;
    }

    @Override
    public MarketplaceManagerService<ProductDTO> getByBaseUrl(String baseUrl) {
        return marketplaceInitializer.getMarketplaces().get(baseUrl);
    }
}
