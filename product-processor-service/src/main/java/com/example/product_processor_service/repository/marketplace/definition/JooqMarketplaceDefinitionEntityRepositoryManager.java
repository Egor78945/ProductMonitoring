package com.example.product_processor_service.repository.marketplace.definition;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqMarketplaceDefinitionEntityRepositoryManager extends JooqMarketplaceDefinitionEntityRepository{
    public JooqMarketplaceDefinitionEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
