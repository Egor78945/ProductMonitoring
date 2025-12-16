package com.example.user_database_manager_service.repository.marketplace.path.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonMarketplacePathDefinitionRepositoryManager extends JooqCommonMarketplacePathDefinitionRepository {
    public JooqCommonMarketplacePathDefinitionRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
