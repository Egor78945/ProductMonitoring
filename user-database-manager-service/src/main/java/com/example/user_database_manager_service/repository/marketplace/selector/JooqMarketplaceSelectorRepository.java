package com.example.user_database_manager_service.repository.marketplace.selector;

import org.jooq.DSLContext;

public abstract class JooqMarketplaceSelectorRepository<MS> extends MarketplaceSelectorRepository<MS> {
    protected final DSLContext dslContext;

    public JooqMarketplaceSelectorRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
