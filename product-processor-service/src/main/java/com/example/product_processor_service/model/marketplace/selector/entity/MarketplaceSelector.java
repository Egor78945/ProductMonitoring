package com.example.product_processor_service.model.marketplace.selector.entity;

import java.util.Objects;

public class MarketplaceSelector {
    private long id;
    private long marketplaceId;
    private String productName;
    private String productPrice;

    public MarketplaceSelector(long id, long marketplaceId, String productName, String productPrice) {
        this.id = id;
        this.marketplaceId = marketplaceId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public MarketplaceSelector() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(long marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MarketplaceSelector that = (MarketplaceSelector) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MarketplaceSelector{" +
                "id=" + id +
                ", marketplaceId=" + marketplaceId +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}
