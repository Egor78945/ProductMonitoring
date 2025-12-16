package com.example.user_database_manager_service.repository.user;

import org.jooq.DSLContext;

public abstract class JooqUserRepository<U> extends UserRepository<U> {
    protected final DSLContext dslContext;

    public JooqUserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
