package com.example.product_processor_service.service.product.processor;

import com.example.product_processor_service.model.product.ProductPublisherDTO;

public interface ProductProcessorService {
    void register(ProductPublisherDTO productDTO);
    void update(String productUrl);
}
