package com.example.product_processor_service.repository.account.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class AccountProductRepositoryManager extends AccountProductRepository {
    public AccountProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
