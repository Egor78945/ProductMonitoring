package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.model.account.entity.Account;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Optional;
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

    @Override
    public Optional<Account> findByUserEmail(String email) {
        return dslContext
                .select(Tables.ACCOUNT, Tables.USERS)
                .from(Tables.ACCOUNT
                        .join(Tables.USERS)
                        .on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID)))
                .where(Tables.USERS.EMAIL.eq(email))
                .fetchOptional(r -> {
                            var ar = r.value1();
                            var ur = r.value2();
                            return new Account(ar.getId(), ar.getUuid(), ur.getUuid(), ar.getStatusId(), Timestamp.from(ar.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()));
                        }
                );
    }
}
