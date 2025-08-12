package com.example.product_processor_service.model.account.product.entity;

import java.util.Objects;
import java.util.UUID;

public class AccountProduct {
    private String url;
    private UUID accountUuid;

    public AccountProduct(String url, UUID accountUuid) {
        this.url = url;
        this.accountUuid = accountUuid;
    }

    public AccountProduct() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountProduct that = (AccountProduct) o;
        return Objects.equals(url, that.url) && Objects.equals(accountUuid, that.accountUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, accountUuid);
    }

    @Override
    public String toString() {
        return "AccountProduct{" +
                "url='" + url + '\'' +
                ", accountUuid=" + accountUuid +
                '}';
    }
}
