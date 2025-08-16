package com.example.product_processor_service.service.marketplace.manager;

import com.example.product_processor_service.model.product.ProductDTO;

public interface MarketplaceManagerService {
    ProductDTO getProductDetails(String url);
}
