package com.example.product_processor_service.service.marketplace.manager;

import java.net.URI;

public interface MarketplaceManagerService<P> {
    P loadProduct(URI url);
}
