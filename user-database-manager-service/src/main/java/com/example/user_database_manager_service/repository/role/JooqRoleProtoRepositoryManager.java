package com.example.user_database_manager_service.repository.role;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqRoleProtoRepositoryManager extends JooqRoleProtoRepository{
    public JooqRoleProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
