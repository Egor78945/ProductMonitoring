package com.example.product_processor_service.repository.marketplace.definition.path;

import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityMarketplacePathDefinitionRepositoryManager extends MarketplacePathDefinitionRepository {
    public EntityMarketplacePathDefinitionRepositoryManager(DSLContext dslContext) {
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
    public void saveAll(List<MarketplacePathDefinition> marketplacePathDefinitionList) {
        for(MarketplacePathDefinition mpd: marketplacePathDefinitionList) {
            save(mpd);
        }
    }
}
