package com.example.product_processor_service.repository.marketplace.definition.path;

import com.example.product_processor_service.util.function.Scrypt;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

public abstract class JooqMarketplacePathDefinitionRepository<MPD> extends MarketplacePathDefinitionRepository<MPD> {
    protected final DSLContext dslContext;

    public JooqMarketplacePathDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean existsByPath(String path) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL.eq(path))
                );
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
