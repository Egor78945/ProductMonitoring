package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

public abstract class JooqMarketplaceDefinitionEntityRepository extends JooqMarketplaceDefinitionRepository<MarketplaceDefinition> {
    public JooqMarketplaceDefinitionEntityRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public MarketplaceDefinition save(MarketplaceDefinition marketplaceDefinition) {
        return dslContext
                .insertInto(Tables.MARKETPLACE_DEFINITION)
                .set(Tables.MARKETPLACE_DEFINITION.NAME, marketplaceDefinition.getName())
                .set(Tables.MARKETPLACE_DEFINITION.PROCESSOR_CLASS_NAME, marketplaceDefinition.getProcessorClassName())
                .returning()
                .fetchOneInto(MarketplaceDefinition.class);
    }

    @Override
    public MarketplaceDefinition update(MarketplaceDefinition marketplaceDefinition) {
        return dslContext
                .update(Tables.MARKETPLACE_DEFINITION)
                .set(Tables.MARKETPLACE_DEFINITION.NAME, marketplaceDefinition.getName())
                .set(Tables.MARKETPLACE_DEFINITION.PROCESSOR_CLASS_NAME, marketplaceDefinition.getProcessorClassName())
                .returning()
                .fetchOneInto(MarketplaceDefinition.class);
    }

    @Override
    public List<MarketplaceDefinition> findAll() {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_DEFINITION)
                .fetchInto(MarketplaceDefinition.class);
    }
}
