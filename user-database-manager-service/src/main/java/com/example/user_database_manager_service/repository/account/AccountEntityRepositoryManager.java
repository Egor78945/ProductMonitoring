package com.example.user_database_manager_service.repository.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.mapper.AccountMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountEntityRepositoryManager implements AccountEntityRepository {
    private final DSLContext dslContext;

    public AccountEntityRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getByUUID(UUID uuid) {
        return dslContext
                .select(Tables.ACCOUNT.ID, Tables.ACCOUNT.UUID, Tables.ACCOUNT.USER_UUID, Tables.ACCOUNT.STATUS_ID, Tables.ACCOUNT.CREATED_AT)
                .from(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchOptional(AccountMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.AccountMessage> getByUserUUID(UUID uuid) {
        return dslContext
                .select(Tables.ACCOUNT.ID, Tables.ACCOUNT.UUID, Tables.ACCOUNT.USER_UUID, Tables.ACCOUNT.STATUS_ID, Tables.ACCOUNT.CREATED_AT)
                .from(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.USER_UUID.eq(uuid))
                .fetchOptional(AccountMapper::mapTo);
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
    public Optional<UserProtoConfiguration.AccountMessage> getById(Long id) {
        return dslContext
                .select(Tables.ACCOUNT.ID, Tables.ACCOUNT.UUID, Tables.ACCOUNT.USER_UUID, Tables.ACCOUNT.STATUS_ID, Tables.ACCOUNT.CREATED_AT)
                .from(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.ID.eq(id))
                .fetchOptional(AccountMapper::mapTo);
    }

    @Override
    public void save(UserProtoConfiguration.AccountMessage entity) {
        dslContext
                .insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.ID, entity.getId())
                .set(Tables.ACCOUNT.UUID, UUID.fromString(entity.getUuid()))
                .set(Tables.ACCOUNT.USER_UUID, UUID.fromString(entity.getUserUuid()))
                .set(Tables.ACCOUNT.STATUS_ID, entity.getStatusId())
                .set(Tables.ACCOUNT.CREATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreatedAt()), ZoneId.systemDefault()))
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
