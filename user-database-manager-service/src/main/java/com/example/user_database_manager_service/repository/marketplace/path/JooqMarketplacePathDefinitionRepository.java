package com.example.user_database_manager_service.repository.marketplace.path;

import org.jooq.DSLContext;

public abstract class JooqMarketplacePathDefinitionRepository<MPD> extends MarketplacePathDefinitionRepository<MPD> {
    protected final DSLContext dslContext;

    public JooqMarketplacePathDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
