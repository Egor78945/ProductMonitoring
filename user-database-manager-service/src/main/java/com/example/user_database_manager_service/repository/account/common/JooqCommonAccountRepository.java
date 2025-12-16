package com.example.user_database_manager_service.repository.account.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqCommonAccountRepository implements CommonAccountRepository{
    protected final DSLContext dslContext;

    public JooqCommonAccountRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public int getCountOfAccountsOfUserByUserUUID(UUID uuid) {
        return dslContext
                .fetchCount(
                        dslContext
                                .select(Tables.ACCOUNT)
                                .from(Tables.ACCOUNT.join(Tables.USERS)
                                        .on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID))
                                        .where(Tables.ACCOUNT.USER_UUID.eq(uuid)))
                );

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
    public boolean existsByUserUUID(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.USER_UUID.eq(uuid))
                );
    }

    @Override
    public boolean existsByName(String name) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.NAME.eq(name))
                );
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

    @Override
    public void deleteById(Long id) {
        dslContext
                .deleteFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteByAccountUuid(UUID accountUuid) {
        dslContext
                .deleteFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(accountUuid))
                .execute();
    }

    @Override
    public void deleteByUserUuid(UUID userUuid) {
        dslContext
                .deleteFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.USER_UUID.eq(userUuid))
                .execute();
    }

    @Override
    public void deleteByUserEmail(String email) {
        dslContext
                .deleteFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.in(
                        dslContext
                                .select(Tables.ACCOUNT.ID)
                                .from(Tables.ACCOUNT.join(Tables.USERS)
                                        .on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID)))
                                .where(Tables.USERS.EMAIL.eq(email))
                ))
                .execute();
    }
}
