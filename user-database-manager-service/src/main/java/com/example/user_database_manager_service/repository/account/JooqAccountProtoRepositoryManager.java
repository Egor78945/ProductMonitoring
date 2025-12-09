package com.example.user_database_manager_service.repository.account;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqAccountProtoRepositoryManager extends JooqAccountProtoRepository {
    public JooqAccountProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
