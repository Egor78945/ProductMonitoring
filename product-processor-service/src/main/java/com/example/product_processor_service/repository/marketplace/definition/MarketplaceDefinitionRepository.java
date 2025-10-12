package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class MarketplaceDefinitionRepository extends JooqRepository<MarketplaceDefinition> {
    protected MarketplaceDefinitionRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public List<MarketplaceDefinition> findAll() {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_DEFINITION)
                .fetchInto(MarketplaceDefinition.class);
    }

    public List<String> findBaseUrlByMarketplaceId(long id) {
        return dslContext
                .select(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL)
                .from(Tables.MARKETPLACE_DEFINITION.join(Tables.MARKETPLACE_PATH_DEFINITION).on(Tables.MARKETPLACE_DEFINITION.ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID)))
                .fetchInto(String.class);
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
    public void saveAll(List<MarketplaceDefinition> marketplaceDefinitionList) {
        for(MarketplaceDefinition md: marketplaceDefinitionList) {
            save(md);
        }
    }
}
