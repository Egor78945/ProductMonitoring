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

public abstract class JooqAccountProtoRepository extends JooqAccountRepository<UserProtoConfiguration.AccountMessage> {
    public JooqAccountProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage entity) {
        return dslContext
                .insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.UUID, UUID.randomUUID())
                .set(Tables.ACCOUNT.USER_UUID, UUID.fromString(entity.getUserUuid()))
                .set(Tables.ACCOUNT.NAME, entity.getName())
                .set(Tables.ACCOUNT.STATUS_ID, entity.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreatedAt()), ZoneId.systemDefault()))
                .set(Tables.ACCOUNT.MAIN, entity.getMain())
                .returning()
                .fetchOne(AccountMapper::map);
    }

    @Override
    public UserProtoConfiguration.AccountMessage update(UserProtoConfiguration.AccountMessage entity) {
        return dslContext
                .update(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.ID, entity.getId())
                .set(Tables.ACCOUNT.UUID, UUID.fromString(entity.getUuid()))
                .set(Tables.ACCOUNT.USER_UUID, UUID.fromString(entity.getUserUuid()))
                .set(Tables.ACCOUNT.NAME, entity.getName())
                .set(Tables.ACCOUNT.STATUS_ID, entity.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreatedAt()), ZoneId.systemDefault()))
                .set(Tables.ACCOUNT.MAIN, entity.getMain())
                .returning()
                .fetchOne(AccountMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getByUUID(UUID uuid) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchOptional(AccountMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getByUserUUID(UUID uuid) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.USER_UUID.eq(uuid))
                .fetchOptional(AccountMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getById(Long id) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(id))
                .fetchOptional(AccountMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getMainByUserUUID(UUID uuid) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.USER_UUID.eq(uuid).and(Tables.ACCOUNT.MAIN.eq(true)))
                .fetchOptional(AccountMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getByAccountName(String accountName) {
        return dslContext
                .selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.NAME.eq(accountName))
                .fetchOptional(AccountMapper::map);
    }
}
