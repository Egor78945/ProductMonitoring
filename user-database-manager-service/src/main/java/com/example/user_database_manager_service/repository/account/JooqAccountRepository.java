package com.example.user_database_manager_service.repository.account;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqAccountRepository extends AccountRepository<UserProtoConfiguration.AccountMessage> {
    protected final DSLContext dslContext;

    public JooqAccountRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
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
    public void delete(UserProtoConfiguration.AccountMessage entity) {
        dslContext
                .deleteFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(UUID.fromString(entity.getUuid()))
                        .and(Tables.ACCOUNT.ID.eq(entity.getId())))
                .execute();
    }
}
