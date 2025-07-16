package com.example.user_database_manager_service.repository.user;

import com.example.user_database_manager_service.model.user.entity.User;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserEntityRepositoryManager implements UserEntityRepository<Long, User> {
    private final DSLContext dslContext;

    public UserEntityRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<User> getById(Long id) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.UUID, Tables.USERS.EMAIL, Tables.USERS.REGISTERED_AT)
                .from(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .fetchOptionalInto(User.class);
    }
    @Override
    public Optional<User> getByUUID(UUID uuid) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.UUID, Tables.USERS.EMAIL, Tables.USERS.REGISTERED_AT)
                .from(Tables.USERS)
                .where(Tables.USERS.UUID.eq(uuid))
                .fetchOptionalInto(User.class);
    }
    @Override
    public Optional<User> getByEmail(String email) {
        return dslContext
                .select(Tables.USERS.ID, Tables.USERS.UUID, Tables.USERS.EMAIL, Tables.USERS.REGISTERED_AT)
                .from(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email))
                .fetchOptionalInto(User.class);
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
    public void save(User user) {
        dslContext
                .insertInto(Tables.USERS)
                .set(Tables.USERS.UUID, user.getUuid())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.STATUS_ID, user.getStatusId())
                .set(Tables.USERS.REGISTERED_AT, user.getRegisteredAt().toLocalDateTime())
                .execute();

    }
}
