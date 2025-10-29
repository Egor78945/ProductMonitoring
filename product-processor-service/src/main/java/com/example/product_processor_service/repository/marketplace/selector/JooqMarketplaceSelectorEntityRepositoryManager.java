package com.example.product_processor_service.repository.marketplace.selector;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqMarketplaceSelectorEntityRepositoryManager extends JooqMarketplaceSelectorEntityRepository {
    public JooqMarketplaceSelectorEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
