package com.example.product_processor_service.model.product;

import java.util.Objects;

public class ProductDTO {
    private String url;
    private String name;
    private int price;

    public ProductDTO(String url, String name, int price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    public ProductDTO() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return price == that.price && Objects.equals(url, that.url) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, price);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
