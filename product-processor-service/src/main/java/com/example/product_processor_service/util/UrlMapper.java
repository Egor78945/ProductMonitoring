package com.example.product_processor_service.service.common;

import java.util.function.Function;

public class MarketplaceUrlMapper {
    public static final Function<String, String> baseUrlExtractor = t -> {
        int c = 0;
        int i = 0;
        while (i < t.length()) {
            if (t.charAt(i) == '/') {
                c++;
            }
            if (c == 3) {
                break;
            }
            i++;
        }
        return t.substring(0, i);
    };
}
