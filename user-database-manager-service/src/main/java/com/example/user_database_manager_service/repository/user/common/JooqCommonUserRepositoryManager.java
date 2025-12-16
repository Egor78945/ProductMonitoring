package com.example.user_database_manager_service.repository.user.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonUserRepositoryManager extends JooqCommonUserRepository{
    public JooqCommonUserRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
