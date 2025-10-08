package com.example.product_processor_service.repository.marketplace.definition.path;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class EntityMarketplacePathDefinitionRepositoryManager extends EntityMarketplacePathDefinitionRepository {
    private final DSLContext dslContext;
    public EntityMarketplacePathDefinitionRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
    @Override
    public boolean existsByPath(String path) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL.eq(path))
                );
    }
}
