package com.example.product_processor_service.repository.marketplace.definition;

import org.jooq.DSLContext;

public abstract class EntityMarketplaceDefinitionRepository extends MarketplaceDefinitionRepository {
    protected EntityMarketplaceDefinitionRepository(DSLContext dslContext) {
        super(dslContext);
    }
}
