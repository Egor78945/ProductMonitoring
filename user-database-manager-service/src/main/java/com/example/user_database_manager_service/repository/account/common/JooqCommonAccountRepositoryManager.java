package com.example.user_database_manager_service.repository.account.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonAccountRepositoryManager extends JooqCommonAccountRepository{
    public JooqCommonAccountRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
