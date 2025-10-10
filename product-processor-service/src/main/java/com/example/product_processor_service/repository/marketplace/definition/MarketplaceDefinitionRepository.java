package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import org.jooq.DSLContext;

import java.util.List;

public abstract class MarketplaceDefinitionRepository {
    protected final DSLContext dslContext;

    protected MarketplaceDefinitionRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public abstract List<MarketplaceDefinition> findAll();
    public abstract List<String> findBaseUrlByMarketplaceId(long id);
}
