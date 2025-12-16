package com.example.user_database_manager_service.repository.account.product.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonAccountProductRepositoryManager extends JooqCommonAccountProductRepository{
    public JooqCommonAccountProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
