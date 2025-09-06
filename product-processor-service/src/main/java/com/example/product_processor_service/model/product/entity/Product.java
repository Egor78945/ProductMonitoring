package com.example.product_processor_service.model.product.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Product {
    private String url;
    private String name;
    private int actualPrice;
    private int pastPrice;
    private Timestamp updatedAt;

    public Product(String url, String name, int actualPrice, int pastPrice, Timestamp updatedAt) {
        this.url = url;
        this.name = name;
        this.actualPrice = actualPrice;
        this.pastPrice = pastPrice;
        this.updatedAt = updatedAt;
    }

    public Product() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getPastPrice() {
        return pastPrice;
    }

    public void setPastPrice(int pastPrice) {
        this.pastPrice = pastPrice;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url);
    }

    @Override
    public String toString() {
        return "Product{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", actualPrice=" + actualPrice +
                ", pastPrice=" + pastPrice +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
