package com.example.user_database_manager_service.repository.marketplace.definition;

import org.jooq.DSLContext;

public abstract class JooqMarketplaceDefinitionRepository<MD> extends MarketplaceDefinitionRepository<MD> {
    protected final DSLContext dslContext;

    public JooqMarketplaceDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
