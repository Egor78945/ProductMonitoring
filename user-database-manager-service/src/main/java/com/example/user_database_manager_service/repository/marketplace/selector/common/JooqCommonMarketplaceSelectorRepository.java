package com.example.user_database_manager_service.repository.marketplace.selector.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

public abstract class JooqCommonMarketplaceSelectorRepository implements CommonMarketplaceSelectorRepository {
    protected final DSLContext dslContext;

    public JooqCommonMarketplaceSelectorRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean existsByMarketplaceId(long marketplaceId) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.MARKETPLACE_SELECTOR.join(Tables.MARKETPLACE_PATH_DEFINITION)
                                        .on(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.ID)))
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.ID.eq(marketplaceId))
                );
    }

    @Override
    public boolean existsById(long id) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.MARKETPLACE_SELECTOR)
                                .where(Tables.MARKETPLACE_SELECTOR.ID.eq(id))
                );
    }
}
