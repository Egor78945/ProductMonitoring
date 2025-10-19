package com.example.product_processor_service.repository.user;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryManager extends UserRepository{
    protected UserRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
