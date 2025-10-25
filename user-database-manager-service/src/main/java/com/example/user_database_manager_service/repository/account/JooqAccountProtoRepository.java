package com.example.user_database_manager_service.repository.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.mapper.AccountMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public abstract class JooqAccountProtoRepository extends AccountProtoRepository {
    protected final DSLContext dslContext;

    public JooqAccountProtoRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage entity) {
        return dslContext
                .insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.UUID, build())
                .set(Tables.ACCOUNT.USER_UUID, UUID.fromString(entity.getUserUuid()))
                .set(Tables.ACCOUNT.NAME, entity.getName())
                .set(Tables.ACCOUNT.STATUS_ID, entity.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreatedAt()), ZoneId.systemDefault()))
                .set(Tables.ACCOUNT.MAIN, entity.getMain())
                .returning()
                .fetchOne(AccountMapper::map);
    }

    public Optional<UserProtoConfiguration.AccountMessage> getByUUID(UUID uuid) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchOptional(AccountMapper::map);
    }

    public Optional<UserProtoConfiguration.AccountMessage> getByUserUUID(UUID uuid) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.USER_UUID.eq(uuid))
                .fetchOptional(AccountMapper::map);
    }

    public Optional<UserProtoConfiguration.AccountMessage> getById(Long id) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(id))
                .fetchOptional(AccountMapper::map);
    }

    public boolean existsByUUID(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.UUID.eq(uuid))
                );
    }

    public boolean existsByUserUUID(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.USER_UUID.eq(uuid))
                );
    }

    public boolean existsByName(String name) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT)
                                .where(Tables.ACCOUNT.NAME.eq(name))
                );
    }

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
