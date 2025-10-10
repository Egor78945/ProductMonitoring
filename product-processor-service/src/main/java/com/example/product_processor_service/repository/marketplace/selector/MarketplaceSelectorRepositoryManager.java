package com.example.product_processor_service.repository.marketplace.selector;

import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MarketplaceSelectorRepositoryManager extends MarketplaceSelectorRepository {
    public MarketplaceSelectorRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public Optional<MarketplaceSelector> findByMarketplaceId(long marketplaceId) {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_SELECTOR)
                .where(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID.eq(marketplaceId))
                .fetchOptionalInto(MarketplaceSelector.class);
    }

    @Override
    public Optional<MarketplaceSelector> findByMarketplaceName(String name) {
        return dslContext
                .select(Tables.MARKETPLACE_SELECTOR)
                .from(Tables.MARKETPLACE_SELECTOR.join(Tables.MARKETPLACE_DEFINITION).on(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID.eq(Tables.MARKETPLACE_DEFINITION.ID)))
                .where(Tables.MARKETPLACE_DEFINITION.NAME.eq(name))
                .fetchOptionalInto(MarketplaceSelector.class);
    }
}
