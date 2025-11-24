package com.example.user_database_manager_service.repository.marketplace.definition;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.marketplace.definition.MarketplaceDefinitionMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class JooqMarketplaceDefinitionProtoRepository extends JooqMarketplaceDefinitionRepository<UserProtoConfiguration.MarketplaceDefinitionMessage> {
    public JooqMarketplaceDefinitionProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.MarketplaceDefinitionMessage save(UserProtoConfiguration.MarketplaceDefinitionMessage entity) {
        return dslContext
                .insertInto(Tables.MARKETPLACE_DEFINITION)
                .set(Tables.MARKETPLACE_DEFINITION.NAME, entity.getName())
                .set(Tables.MARKETPLACE_DEFINITION.PROCESSOR_CLASS_NAME, entity.getProcessorClassName())
                .returning()
                .fetchOne(MarketplaceDefinitionMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.MarketplaceDefinitionMessage update(UserProtoConfiguration.MarketplaceDefinitionMessage entity) {
        return dslContext
                .update(Tables.MARKETPLACE_DEFINITION)
                .set(Tables.MARKETPLACE_DEFINITION.ID, entity.getId())
                .set(Tables.MARKETPLACE_DEFINITION.NAME, entity.getName())
                .set(Tables.MARKETPLACE_DEFINITION.PROCESSOR_CLASS_NAME, entity.getProcessorClassName())
                .returning()
                .fetchOne(MarketplaceDefinitionMapper::mapTo);
    }

    @Override
    public List<UserProtoConfiguration.MarketplaceDefinitionMessage> findAll() {
        return dslContext
                .selectFrom(Tables.MARKETPLACE_DEFINITION)
                .fetch(MarketplaceDefinitionMapper::mapTo);
    }
}
