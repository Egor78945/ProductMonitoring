package com.example.user_database_manager_service.repository.role;

import org.jooq.DSLContext;

public abstract class JooqRoleRepository<R> extends RoleRepository<R> {
    protected final DSLContext dslContext;

    public JooqRoleRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
