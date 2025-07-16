package com.example.user_database_manager_service.repository.account;

import com.example.user_database_manager_service.model.account.entity.Account;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountEntityRepositoryManager implements AccountEntityRepository {
    private final DSLContext dslContext;

    public AccountEntityRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Account> getByUUID(UUID uuid) {
        return dslContext
                .select(Tables.ACCOUNT.ID, Tables.ACCOUNT.UUID, Tables.ACCOUNT.USER_UUID, Tables.ACCOUNT.STATUS_ID, Tables.ACCOUNT.CREATED_AT)
                .from(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchOptionalInto(Account.class);
    }

    @Override
    public Optional<Account> getByUserUUID(UUID uuid) {
        return dslContext
                .select(Tables.ACCOUNT.ID, Tables.ACCOUNT.UUID, Tables.ACCOUNT.USER_UUID, Tables.ACCOUNT.STATUS_ID, Tables.ACCOUNT.CREATED_AT)
                .from(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.USER_UUID.eq(uuid))
                .fetchOptionalInto(Account.class);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.UUID.eq(uuid))
                );
    }

    @Override
    public Optional<Account> getById(Long id) {
        return dslContext
                .select(Tables.ACCOUNT.ID, Tables.ACCOUNT.UUID, Tables.ACCOUNT.USER_UUID, Tables.ACCOUNT.STATUS_ID, Tables.ACCOUNT.CREATED_AT)
                .from(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(id))
                .fetchOptionalInto(Account.class);
    }

    @Override
    public void save(Account entity) {
        dslContext
                .insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.ID, entity.getId())
                .set(Tables.ACCOUNT.UUID, entity.getUuid())
                .set(Tables.ACCOUNT.USER_UUID, entity.getUserUUID())
                .set(Tables.ACCOUNT.STATUS_ID, entity.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, entity.getCreatedAt().toLocalDateTime())
                .execute();
    }

    @Override
    public boolean existsById(Long id) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.ID.eq(id))
                );
    }
}
