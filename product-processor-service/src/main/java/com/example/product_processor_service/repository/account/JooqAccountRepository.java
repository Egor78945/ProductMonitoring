package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.util.function.Scrypt;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqAccountRepository<A> extends AccountRepository<A> {
    protected final DSLContext dslContext;

    public JooqAccountRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean existsByUuid(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.UUID.eq(uuid))
                );
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
