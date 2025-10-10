package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.model.account.entity.Account;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.Optional;
import java.util.UUID;

public abstract class AccountRepository {
    protected DSLContext dslContext;

    public AccountRepository(DSLContext dslContext) {
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

    public abstract Optional<Account> findByUserEmail(String email);
}
