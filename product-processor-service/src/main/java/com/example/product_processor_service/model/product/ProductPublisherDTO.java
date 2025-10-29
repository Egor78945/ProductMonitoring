package com.example.product_processor_service.model.product;

import java.io.Serializable;
import java.util.Objects;

public class ProductPublisherDTO implements Serializable {
    private String publisherAccountUuid;
    private String productUri;

    public ProductPublisherDTO(String publisherAccountUuid, String productUri) {
        this.publisherAccountUuid = publisherAccountUuid;
        this.productUri = productUri;
    }

    public ProductPublisherDTO() {
    }

    public String getPublisherAccountUuid() {
        return publisherAccountUuid;
    }

    public void setPublisherAccountUuid(String publisherAccountUuid) {
        this.publisherAccountUuid = publisherAccountUuid;
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
        return Objects.equals(publisherAccountUuid, that.publisherAccountUuid) && Objects.equals(productUri, that.productUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherAccountUuid, productUri);
    }

    @Override
    public String toString() {
        return "ProductPublisherDTO{" +
                "publisherAccountUuid='" + publisherAccountUuid + '\'' +
                ", productUri='" + productUri + '\'' +
                '}';
    }
}
