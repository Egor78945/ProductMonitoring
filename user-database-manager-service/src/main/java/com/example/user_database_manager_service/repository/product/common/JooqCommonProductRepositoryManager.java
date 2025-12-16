package com.example.user_database_manager_service.repository.product.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonProductRepositoryManager extends JooqCommonProductRepository{
    public JooqCommonProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
