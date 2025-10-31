package com.example.user_database_manager_service.repository.user;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqUserRepository<U> extends UserRepository<U> {
    protected final DSLContext dslContext;

    public JooqUserRepository(DSLContext dslContext) {
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
}
