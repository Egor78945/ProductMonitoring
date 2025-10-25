package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AccountRepository extends JooqRepository<Account> {
    public AccountRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public Account save(Account account) {
        return dslContext
                .insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.UUID, account.getUuid())
                .set(Tables.ACCOUNT.USER_UUID, account.getUserUUID())
                .set(Tables.ACCOUNT.NAME, account.getName())
                .set(Tables.ACCOUNT.STATUS_ID, account.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, account.getCreatedAt().toLocalDateTime())
                .set(Tables.ACCOUNT.MAIN, account.isMain())
                .returning()
                .fetchOneInto(Account.class);
    }

    @Override
    public Account update(Account account) {
        return dslContext
                .update(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.UUID, account.getUuid())
                .set(Tables.ACCOUNT.USER_UUID, account.getUserUUID())
                .set(Tables.ACCOUNT.NAME, account.getName())
                .set(Tables.ACCOUNT.STATUS_ID, account.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, account.getCreatedAt().toLocalDateTime())
                .set(Tables.ACCOUNT.MAIN, account.isMain())
                .returning()
                .fetchOneInto(Account.class);
    }

    @Override
    public void saveAll(List<Account> accountList) {
        for (Account a : accountList) {
            save(a);
        }
    }

    public boolean existsByUuid(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.UUID.eq(uuid))
                );
    }

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
                            return new Account(ar.getId(), ar.getUuid(), ur.getUuid(), ar.getName(), ar.getStatusId(), Timestamp.from(ar.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()), ar.getMain());
                        }
                );
    }

    public List<Account> findAllByUuid(UUID uuid) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchInto(Account.class);
    }
}
