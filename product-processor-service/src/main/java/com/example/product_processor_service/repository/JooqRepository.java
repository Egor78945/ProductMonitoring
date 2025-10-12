package com.example.product_processor_service.repository;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DSLContext;

public abstract class JooqRepository<T> extends Repository<T>{
    protected final DSLContext dslContext;

    protected JooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public final void transactional(Scrypt scrypt) {
        System.out.println("DSL CONTEXT IS NULL: " + dslContext);
        dslContext.transaction(scrypt::execute);
    }
}
