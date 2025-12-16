package com.example.user_database_manager_service.repository.user.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqCommonUserRepository implements CommonUserRepository{
    protected final DSLContext dslContext;

    public JooqCommonUserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean existsBy(Long id, UUID uuid, String email) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.ID.eq(id)
                                        .and(Tables.USERS.UUID.eq(uuid)
                                                .and(Tables.USERS.EMAIL.eq(email))))
                );
    }

    @Override
    public boolean existsById(Long id) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.ID.eq(id))
                );
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.UUID.eq(uuid))
                );
    }

    @Override
    public boolean existsByEmail(String email) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.EMAIL.eq(email))
                );
    }

    @Override
    public void deleteById(Long id) {
        dslContext
                .deleteFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        dslContext
                .deleteFrom(Tables.USERS)
                .where(Tables.USERS.UUID.eq(uuid))
                .execute();
    }

    @Override
    public void deleteByEmail(String email) {
        dslContext
                .deleteFrom(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email))
                .execute();
    }
}
