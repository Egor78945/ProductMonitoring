package com.example.user_database_manager_service.repository.account.product;

import org.jooq.DSLContext;

public abstract class JooqAccountProductRepository<AP> extends AccountProductRepository<AP> {
    protected final DSLContext dslContext;

    public JooqAccountProductRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
