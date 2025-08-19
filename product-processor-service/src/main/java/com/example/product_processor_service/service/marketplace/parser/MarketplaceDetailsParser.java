package com.example.product_processor_service.service.marketplace.parser;

public interface MarketplaceDetailsParser<S> {
    String parseName(S source);

    int parsePrice(S source);
}
