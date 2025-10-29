package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.repository.Repository;

import java.util.List;
import java.util.UUID;

public abstract class AccountProductRepository<AP> extends Repository<AP> {
    public abstract List<AccountProduct> findAllByUrl(String url);

    public abstract boolean existsByUrlAndUserEmail(String url, String userEmail);

    public abstract boolean existsByUrlAndAccountUuid(String url, UUID accountUuid);
}
