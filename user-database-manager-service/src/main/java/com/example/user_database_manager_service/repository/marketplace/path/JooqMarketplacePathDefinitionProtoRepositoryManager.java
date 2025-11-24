package com.example.user_database_manager_service.repository.marketplace.path;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqMarketplacePathDefinitionProtoRepositoryManager extends JooqMarketplacePathDefinitionProtoRepository{
    public JooqMarketplacePathDefinitionProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
