package com.example.product_processor_service.service.marketplace.parser;

import com.microsoft.playwright.Page;

public class PyaterochkaMarketplaceDetailsParserManager implements MarketplaceDetailsParser<Page> {
    @Override
    public String parseName(Page page) {
        return "";
    }

    @Override
    public int parsePrice(Page page) {
        return 0;
    }
}
