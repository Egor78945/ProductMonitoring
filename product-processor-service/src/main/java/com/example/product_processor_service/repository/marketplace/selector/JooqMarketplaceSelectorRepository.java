package com.example.product_processor_service.repository.marketplace.selector;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DSLContext;

public abstract class JooqMarketplaceSelectorRepository<MS> extends MarketplaceSelectorRepository<MS> {
    protected final DSLContext dslContext;

    public JooqMarketplaceSelectorRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
