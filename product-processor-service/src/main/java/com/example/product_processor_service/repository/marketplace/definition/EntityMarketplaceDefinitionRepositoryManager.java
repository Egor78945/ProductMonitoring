package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityMarketplaceDefinitionRepositoryManager extends EntityMarketplaceDefinitionRepository<MarketplaceDefinition> {
    private final DSLContext dslContext;

    public EntityMarketplaceDefinitionRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<MarketplaceDefinition> findAll() {
        return null;
    }
}
