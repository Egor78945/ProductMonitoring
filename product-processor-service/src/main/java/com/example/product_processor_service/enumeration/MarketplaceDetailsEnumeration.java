package com.example.product_processor_service.enumeration;

public enum MarketplaceDetailsEnumeration {
    PYATEROCHKA("5ka");
    private final String name;
    MarketplaceDetailsEnumeration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
