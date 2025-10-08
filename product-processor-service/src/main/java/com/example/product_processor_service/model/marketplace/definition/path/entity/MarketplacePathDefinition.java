package com.example.product_processor_service.model.marketplace.definition.path.entity;

import java.util.Objects;

public class MarketplacePathDefinition {
    private Long marketplaceId;
    private String baseUrl;

    public MarketplacePathDefinition(Long marketplaceId, String baseUrl) {
        this.marketplaceId = marketplaceId;
        this.baseUrl = baseUrl;
    }

    public MarketplacePathDefinition() {
    }

    public Long getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(Long marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MarketplacePathDefinition that = (MarketplacePathDefinition) o;
        return Objects.equals(baseUrl, that.baseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(baseUrl);
    }

    @Override
    public String toString() {
        return "MarketplacePathDefinition{" +
                "marketplaceId=" + marketplaceId +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
