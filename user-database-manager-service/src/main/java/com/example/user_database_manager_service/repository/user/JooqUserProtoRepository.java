package com.example.user_database_manager_service.repository.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.user.mapper.UserMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public abstract class JooqUserProtoRepository extends JooqUserRepository {
    public JooqUserProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage user) {
        return dslContext
                .insertInto(Tables.USERS)
                .set(Tables.USERS.UUID, build())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.STATUS_ID, user.getStatusId())
                .set(Tables.USERS.REGISTERED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(user.getRegisteredAt()), ZoneId.systemDefault()))
                .returning()
                .fetchOne(UserMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> getById(Long id) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .fetchOptional(UserMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> getByUUID(UUID uuid) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.UUID.eq(uuid))
                .fetchOptional(UserMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> getByEmail(String email) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email))
                .fetchOptional(UserMapper::map);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> getByAccountName(String accountName) {
        return dslContext
                .select(Tables.USERS)
                .from(Tables.USERS
                        .join(Tables.ACCOUNT)
                        .on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID)))
                .where(Tables.ACCOUNT.NAME.eq(accountName))
                .fetchOptional(u -> UserMapper.map(u.value1()));
    }
}
