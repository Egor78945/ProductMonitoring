package com.example.product_processor_service.repository.marketplace.definition.path;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class MarketplacePathDefinitionRepository {
    protected DSLContext dslContext;

    public MarketplacePathDefinitionRepository(DSLContext dslContext) {
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

    public List<String> getAllPaths() {
        return dslContext
                .select(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL)
                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                .fetchInto(String.class);
    }
}
