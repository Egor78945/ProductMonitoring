package com.example.product_processor_service.service.marketplace.parser;

public interface MarketplaceDetailsParser {
    String parseName(String url);
    int parsePrice(String url);
}
