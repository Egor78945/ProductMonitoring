package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;

import java.util.UUID;

public abstract class AccountProductRepository<P extends AccountProduct> {
    public abstract void save(P product);
    public abstract boolean existsByUrlAndUserEmail(String url, String userEmail);
    public abstract boolean existsByUrlAndAccountUuid(String url, UUID accountUuid);
}
