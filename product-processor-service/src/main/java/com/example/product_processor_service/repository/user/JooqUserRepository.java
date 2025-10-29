package com.example.product_processor_service.repository.user;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DSLContext;

public abstract class JooqUserRepository<U> extends UserRepository<U> {
    protected final DSLContext dslContext;

    public JooqUserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
