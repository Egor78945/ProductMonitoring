package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.product.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AccountProductRepository<P extends Product> {
    public abstract List<P> getAllByAccountUuidAndPageAndPageSize(UUID uuid, int page, int pageSize);
    public abstract Optional<P> getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
    public abstract boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
}
