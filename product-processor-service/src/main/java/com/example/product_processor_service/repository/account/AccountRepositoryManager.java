package com.example.product_processor_service.repository.account;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryManager extends AccountRepository {
    public AccountRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
