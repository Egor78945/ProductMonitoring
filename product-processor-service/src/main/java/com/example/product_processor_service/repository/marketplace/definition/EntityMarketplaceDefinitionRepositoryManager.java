package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import jakarta.annotation.PostConstruct;
import nu.studer.sample.Tables;
import org.jooq.Case;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityMarketplaceDefinitionRepositoryManager extends EntityMarketplaceDefinitionRepository {
    public EntityMarketplaceDefinitionRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<MarketplaceDefinition> findAll() {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_DEFINITION)
                .fetchInto(MarketplaceDefinition.class);
    }

    @Override
    public List<String> findBaseUrlByMarketplaceId(long id) {
        return dslContext
                .select(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL)
                .from(Tables.MARKETPLACE_DEFINITION.join(Tables.MARKETPLACE_PATH_DEFINITION).on(Tables.MARKETPLACE_DEFINITION.ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID)))
                .fetchInto(String.class);
    }
}
