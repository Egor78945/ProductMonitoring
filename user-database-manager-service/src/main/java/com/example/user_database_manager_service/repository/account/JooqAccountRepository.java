package com.example.user_database_manager_service.repository.account;

import org.jooq.DSLContext;

public abstract class JooqAccountRepository<A> extends AccountRepository<A> {
    protected final DSLContext dslContext;

    public JooqAccountRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
