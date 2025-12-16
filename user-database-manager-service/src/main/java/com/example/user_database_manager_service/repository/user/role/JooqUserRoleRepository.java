package com.example.user_database_manager_service.repository.user.role;

import org.jooq.DSLContext;

public abstract class JooqUserRoleRepository<UR> extends UserRoleRepository<UR> {
    protected final DSLContext dslContext;

    public JooqUserRoleRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}