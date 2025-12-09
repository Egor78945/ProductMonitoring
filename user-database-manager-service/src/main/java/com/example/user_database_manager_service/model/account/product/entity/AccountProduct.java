package com.example.user_database_manager_service.model.account.product.entity;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

public class AccountProduct {
    private UUID accountUuid;
    private URI productUrl;

    public AccountProduct(UUID accountUuid, URI productUrl) {
        this.accountUuid = accountUuid;
        this.productUrl = productUrl;
    }

    public AccountProduct() {
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

    public URI getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(URI productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountProduct that = (AccountProduct) o;
        return Objects.equals(accountUuid, that.accountUuid) && Objects.equals(productUrl, that.productUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountUuid, productUrl);
    }

    @Override
    public String toString() {
        return "AccountProduct{" +
                "accountUuid=" + accountUuid +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}
