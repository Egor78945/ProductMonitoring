package com.example.user_database_manager_service.repository.marketplace.definition.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

public abstract class JooqCommonMarketplaceDefinitionRepository implements CommonMarketplaceDefinitionRepository {
    protected final DSLContext dslContext;

    public JooqCommonMarketplaceDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean existsByName(String name) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.MARKETPLACE_DEFINITION)
                                .where(Tables.MARKETPLACE_DEFINITION.NAME.eq(name))

                );
    }

    @Override
    public boolean existsById(long id) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.MARKETPLACE_DEFINITION)
                                .where(Tables.MARKETPLACE_DEFINITION.ID.eq(id))
                );
    }
}
