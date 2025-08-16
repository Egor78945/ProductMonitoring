package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;

public abstract class AccountProductRepository<P extends AccountProduct> {
    public abstract void save(P product);
}
