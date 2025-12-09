package com.example.user_database_manager_service.repository.marketplace.path;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

public abstract class JooqMarketplacePathDefinitionRepository<MPD> extends MarketplacePathDefinitionRepository<MPD> {
    protected final DSLContext dslContext;

    public JooqMarketplacePathDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean existsByMarketplaceDomain(String marketplaceDomain) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL.eq(marketplaceDomain))
                );
    }

    @Override
    public boolean existsByMarketplaceUrl(String marketplaceUrl) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL.eq(marketplaceUrl))
                );
    }

    @Override
    public boolean existsById(long id) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.ID.eq(id))
                );
    }
}
