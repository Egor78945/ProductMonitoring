package com.example.user_database_manager_service.repository.user.role;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserRoleProtoRepositoryManager extends JooqUserRoleProtoRepository {
    public JooqUserRoleProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
