package com.example.user_database_manager_service.repository.marketplace.selector;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqMarketplaceSelectorProtoRepositoryManager extends JooqMarketplaceSelectorProtoRepository {
    public JooqMarketplaceSelectorProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
