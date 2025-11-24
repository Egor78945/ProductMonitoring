package com.example.user_database_manager_service.repository.marketplace.definition;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqMarketplaceDefinitionProtoRepositoryManager extends JooqMarketplaceDefinitionProtoRepository{
    public JooqMarketplaceDefinitionProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
