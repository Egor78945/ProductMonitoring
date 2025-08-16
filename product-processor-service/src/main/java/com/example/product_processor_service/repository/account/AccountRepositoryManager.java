package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.model.account.entity.Account;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountRepositoryManager extends AccountRepository<Account> {
    private final DSLContext dslContext;
    public AccountRepositoryManager(DSLContext dsl) {
        this.dslContext = dsl;
    }
    @Override
    public boolean existsByUuid(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.UUID.eq(uuid))
                );
    }
}
