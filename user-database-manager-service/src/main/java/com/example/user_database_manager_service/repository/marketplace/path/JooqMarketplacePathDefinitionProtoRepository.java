package com.example.user_database_manager_service.repository.marketplace.path;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.marketplace.path.mapper.MarketplacePathDefinitionMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class JooqMarketplacePathDefinitionProtoRepository extends JooqMarketplacePathDefinitionRepository<UserProtoConfiguration.MarketplacePathDefinitionMessage> {
    public JooqMarketplacePathDefinitionProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.MarketplacePathDefinitionMessage save(UserProtoConfiguration.MarketplacePathDefinitionMessage entity) {
        return dslContext
                .insertInto(Tables.MARKETPLACE_PATH_DEFINITION)
                .set(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID, entity.getMarketplaceId())
                .set(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL, entity.getBaseUrl())
                .returning()
                .fetchOne(MarketplacePathDefinitionMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.MarketplacePathDefinitionMessage update(UserProtoConfiguration.MarketplacePathDefinitionMessage entity) {
        return dslContext
                .update(Tables.MARKETPLACE_PATH_DEFINITION)
                .set(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID, entity.getMarketplaceId())
                .set(Tables.MARKETPLACE_PATH_DEFINITION.BASE_URL, entity.getBaseUrl())
                .returning()
                .fetchOne(MarketplacePathDefinitionMapper::mapTo);
    }

    @Override
    public List<UserProtoConfiguration.MarketplacePathDefinitionMessage> findAllSupported() {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_PATH_DEFINITION)
                .fetch(MarketplacePathDefinitionMapper::mapTo);
    }

    @Override
    public List<UserProtoConfiguration.MarketplacePathDefinitionMessage> findAllByMarketplaceDefinitionId(Long id) {
        return dslContext
                .select(Tables.MARKETPLACE_PATH_DEFINITION)
                .from(Tables.MARKETPLACE_DEFINITION.join(Tables.MARKETPLACE_PATH_DEFINITION)
                        .on(Tables.MARKETPLACE_DEFINITION.ID.eq(Tables.MARKETPLACE_PATH_DEFINITION.MARKETPLACE_ID)))
                .fetch(r -> MarketplacePathDefinitionMapper.mapTo(r.value1()));
    }
}
