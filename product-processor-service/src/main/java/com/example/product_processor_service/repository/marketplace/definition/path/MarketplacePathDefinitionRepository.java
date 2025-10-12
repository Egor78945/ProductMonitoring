package com.example.product_processor_service.repository.marketplace.definition.path;

import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class MarketplacePathDefinitionRepository extends JooqRepository<MarketplacePathDefinition> {
    public MarketplacePathDefinitionRepository(DSLContext dslContext) {
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

    public boolean existsByPath(String path) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                                .where(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL.eq(path))
                );
    }

    public List<String> getAllPaths() {
        return dslContext
                .select(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL)
                .from(Tables.MARKETPLACE_PATH_DEFINITION)
                .fetchInto(String.class);
    }
}
