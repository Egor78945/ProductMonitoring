package com.example.product_processor_service.model.product;

import com.example.product_processor_service.model.product.entity.Product;

import java.sql.Timestamp;
import java.util.Objects;

public class ProductRegistrationModel extends Product {
    protected String accountUuid;

    public ProductRegistrationModel(String url, String name, int actualPrice, int pastPrice, long updatedAt, String accountUuid) {
        super(url, name, actualPrice, pastPrice, updatedAt);
        this.accountUuid = accountUuid;
    }

    public ProductRegistrationModel() {
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductRegistrationModel that = (ProductRegistrationModel) o;
        return Objects.equals(accountUuid, that.accountUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountUuid);
    }

    @Override
    public String toString() {
        return "ProductRegistrationModel{" +
                "accountUuid='" + accountUuid + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", actualPrice=" + actualPrice +
                ", pastPrice=" + pastPrice +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
