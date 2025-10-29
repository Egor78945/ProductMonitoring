package com.example.user_database_manager_service.repository.account.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqAccountProductEntityRepositoryManager extends JooqAccountProductEntityRepository{
    public JooqAccountProductEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
