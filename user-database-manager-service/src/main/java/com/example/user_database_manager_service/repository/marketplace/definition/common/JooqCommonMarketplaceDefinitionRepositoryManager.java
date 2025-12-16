package com.example.user_database_manager_service.repository.marketplace.definition.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonMarketplaceDefinitionRepositoryManager extends JooqCommonMarketplaceDefinitionRepository {
    public JooqCommonMarketplaceDefinitionRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
