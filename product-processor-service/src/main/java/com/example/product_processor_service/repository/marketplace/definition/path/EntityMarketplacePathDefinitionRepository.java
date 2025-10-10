package com.example.product_processor_service.repository.marketplace.definition.path;

import org.jooq.DSLContext;

public abstract class EntityMarketplacePathDefinitionRepository extends MarketplacePathDefinitionRepository {
    public EntityMarketplacePathDefinitionRepository(DSLContext dslContext) {
        super(dslContext);
    }
}
