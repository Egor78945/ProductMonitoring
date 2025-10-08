package com.example.product_processor_service.model.product;

import java.io.Serializable;
import java.util.Objects;

public class ProductPublisherDTO implements Serializable {
    private String publisherEmail;
    private String productUri;

    public ProductPublisherDTO(String publisherEmail, String productUri) {
        this.publisherEmail = publisherEmail;
        this.productUri = productUri;
    }

    public ProductPublisherDTO() {
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public String getProductUri() {
        return productUri;
    }

    public void setProductUri(String productUri) {
        this.productUri = productUri;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductPublisherDTO that = (ProductPublisherDTO) o;
        return Objects.equals(publisherEmail, that.publisherEmail) && Objects.equals(productUri, that.productUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherEmail, productUri);
    }

    @Override
    public String toString() {
        return "ProductPublisherDTO{" +
                "publisherEmail='" + publisherEmail + '\'' +
                ", productUri='" + productUri + '\'' +
                '}';
    }
}
