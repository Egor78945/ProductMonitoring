package com.example.user_database_manager_service.repository.account.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqAccountProductProtoRepositoryManager extends JooqAccountProductProtoRepository{
    public JooqAccountProductProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
