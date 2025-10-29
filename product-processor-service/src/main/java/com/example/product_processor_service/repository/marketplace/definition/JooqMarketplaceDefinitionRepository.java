package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DSLContext;

public abstract class JooqMarketplaceDefinitionRepository<MD> extends MarketplaceDefinitionRepository<MD>{
    protected final DSLContext dslContext;

    public JooqMarketplaceDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
