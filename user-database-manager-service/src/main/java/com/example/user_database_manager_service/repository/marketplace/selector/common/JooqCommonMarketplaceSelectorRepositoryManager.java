package com.example.user_database_manager_service.repository.marketplace.selector.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonMarketplaceSelectorRepositoryManager extends JooqCommonMarketplaceSelectorRepository{
    public JooqCommonMarketplaceSelectorRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
