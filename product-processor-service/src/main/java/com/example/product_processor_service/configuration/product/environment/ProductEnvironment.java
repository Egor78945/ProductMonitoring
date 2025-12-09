package com.example.product_processor_service.configuration.product.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductEnvironment {
    private final int PRODUCT_READ_COUNT;

    public ProductEnvironment(@Value("${product.read.count}") int PRODUCT_READ_COUNT) {
        this.PRODUCT_READ_COUNT = PRODUCT_READ_COUNT;
    }

    public int getPRODUCT_READ_COUNT() {
        return PRODUCT_READ_COUNT;
    }
}
