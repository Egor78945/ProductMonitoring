package com.example.product_processor_service.repository.marketplace.definition.path;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqMarketplacePathDefinitionEntityRepositoryManager extends JooqMarketplacePathDefinitionEntityRepository {
    public JooqMarketplacePathDefinitionEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
