package com.example.product_processor_service.service.marketplace.manager;

import com.example.product_processor_service.model.product.ProductDTO;

import java.net.URI;

public interface MarketplaceManagerService {
    void loadProduct(URI url);
}
