package com.example.user_database_manager_service.repository.product;

import org.jooq.DSLContext;

public abstract class JooqProductRepository<P> extends ProductRepository<P> {
    protected final DSLContext dslContext;

    public JooqProductRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
