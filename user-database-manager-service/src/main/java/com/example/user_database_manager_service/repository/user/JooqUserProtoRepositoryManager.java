package com.example.user_database_manager_service.repository.user;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserProtoRepositoryManager extends JooqUserProtoRepository {
    public JooqUserProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
