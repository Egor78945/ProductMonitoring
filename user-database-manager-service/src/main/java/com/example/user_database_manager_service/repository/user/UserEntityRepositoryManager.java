package com.example.user_database_manager_service.repository.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.service.user.mapper.UserMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserEntityRepositoryManager implements UserEntityRepository {
    private final DSLContext dslContext;

    public UserEntityRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> getById(Long id) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.UUID, Tables.USERS.EMAIL, Tables.USERS.REGISTERED_AT)
                .from(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .fetchOptional(UserMapper::mapTo);
    }
    @Override
    public Optional<UserProtoConfiguration.UserMessage> getByUUID(UUID uuid) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.UUID, Tables.USERS.EMAIL, Tables.USERS.REGISTERED_AT)
                .from(Tables.USERS)
                .where(Tables.USERS.UUID.eq(uuid))
                .fetchOptional(UserMapper::mapTo);
    }
    @Override
    public Optional<UserProtoConfiguration.UserMessage> getByEmail(String email) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.UUID, Tables.USERS.EMAIL, Tables.USERS.REGISTERED_AT)
                .from(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email))
                .fetchOptional(UserMapper::mapTo);
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
    public UUID save(UserProtoConfiguration.UserMessage user) {
        return dslContext
                .insertInto(Tables.USERS)
                .set(Tables.USERS.UUID, generateUnbusyUUID())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.STATUS_ID, user.getStatusId())
                .set(Tables.USERS.REGISTERED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(user.getRegisteredAt()), ZoneId.systemDefault()))
                .returning(Tables.USERS.UUID)
                .fetchOne(Tables.USERS.UUID, UUID.class);
    }

    private UUID generateUnbusyUUID() {
        int lap = 0;
        UUID uuid = UUID.randomUUID();
        while(lap < 10){
            if(!existsByUUID(uuid)) {
                return uuid;
            }
            lap++;
        }
        throw new ProcessingException(ExceptionMessage.FAILED_TO_CREATE.getMessage());
    }
}
