package com.example.product_processor_service.repository.marketplace.definition;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class EntityMarketplaceDefinitionRepositoryManager extends MarketplaceDefinitionRepository {
    public EntityMarketplaceDefinitionRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
