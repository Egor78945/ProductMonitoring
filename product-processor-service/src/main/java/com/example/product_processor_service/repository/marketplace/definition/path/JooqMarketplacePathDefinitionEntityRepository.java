package com.example.product_processor_service.repository.marketplace.definition.path;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class JooqMarketplacePathDefinitionEntityRepository extends JooqMarketplacePathDefinitionRepository<MarketplacePathDefinition> {
    public JooqMarketplacePathDefinitionEntityRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public MarketplacePathDefinition save(MarketplacePathDefinition marketplacePathDefinition) {
        return dslContext
                .insertInto(Tables.MARKETPLACE_PATH_DEFINITION)
                .set(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID, marketplacePathDefinition.getMarketplaceId())
                .set(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL, marketplacePathDefinition.getBaseUrl())
                .returning()
                .fetchOneInto(MarketplacePathDefinition.class);
    }

    @Override
    public MarketplacePathDefinition update(MarketplacePathDefinition marketplacePathDefinition) {
        return dslContext
                .insertInto(Tables.MARKETPLACE_PATH_DEFINITION)
                .set(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID, marketplacePathDefinition.getMarketplaceId())
                .set(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL, marketplacePathDefinition.getBaseUrl())
                .returning()
                .fetchOneInto(MarketplacePathDefinition.class);
    }

    @Override
    public List<MarketplacePathDefinition> findAll() {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_PATH_DEFINITION)
                .fetchInto(MarketplacePathDefinition.class);
    }

    @Override
    public List<MarketplacePathDefinition> findByMarketplaceId(long id) {
        return dslContext
                .select(Tables.MARKETPLACE_PATH_DEFINITION)
                .from(Tables.MARKETPLACE_DEFINITION.join(Tables.MARKETPLACE_PATH_DEFINITION)
                        .on(Tables.MARKETPLACE_DEFINITION.ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID)))
                .where(Tables.MARKETPLACE_DEFINITION.ID.eq(id))
                .fetchInto(MarketplacePathDefinition.class);
    }
}
