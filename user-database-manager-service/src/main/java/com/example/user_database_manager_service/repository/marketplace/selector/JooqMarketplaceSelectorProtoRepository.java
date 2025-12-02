package com.example.user_database_manager_service.repository.marketplace.selector;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.marketplace.selector.mapper.MarketplaceSelectorMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.Optional;

public abstract class JooqMarketplaceSelectorProtoRepository extends JooqMarketplaceSelectorRepository<UserProtoConfiguration.MarketplaceSelectorMessage> {
    public JooqMarketplaceSelectorProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage save(UserProtoConfiguration.MarketplaceSelectorMessage entity) {
        return dslContext
                .insertInto(Tables.MARKETPLACE_SELECTOR)
                .set(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID, entity.getMarketplaceId())
                .set(Tables.MARKETPLACE_SELECTOR.PRODUCT_NAME, entity.getProductName())
                .set(Tables.MARKETPLACE_SELECTOR.PRODUCT_PRICE, entity.getProductPrice())
                .returning()
                .fetchOne(MarketplaceSelectorMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage update(UserProtoConfiguration.MarketplaceSelectorMessage entity) {
        return dslContext
                .update(Tables.MARKETPLACE_SELECTOR)
                .set(Tables.MARKETPLACE_SELECTOR.ID, entity.getId())
                .set(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID, entity.getMarketplaceId())
                .set(Tables.MARKETPLACE_SELECTOR.PRODUCT_NAME, entity.getProductName())
                .set(Tables.MARKETPLACE_SELECTOR.PRODUCT_PRICE, entity.getProductPrice())
                .returning()
                .fetchOne(MarketplaceSelectorMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.MarketplaceSelectorMessage> findByMarketplaceId(long marketplaceId) {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_SELECTOR)
                .where(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID.eq(marketplaceId))
                .fetchOptional(MarketplaceSelectorMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.MarketplaceSelectorMessage> findByMarketplaceName(String marketplaceName) {
        return dslContext
                .select(Tables.MARKETPLACE_SELECTOR)
                .from(Tables.MARKETPLACE_SELECTOR.join(Tables.MARKETPLACE_PATH_DEFINITION)
                        .on(Tables.MARKETPLACE_SELECTOR.MARKETPLACE_ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.ID))
                        .join(Tables.MARKETPLACE_DEFINITION)
                        .on(Tables.MARKETPLACE_DEFINITION.ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID)))
                .where(Tables.MARKETPLACE_DEFINITION.NAME.eq(marketplaceName))
                .fetchOptional(r -> MarketplaceSelectorMapper.mapTo(r.value1()));
    }
}
