package com.example.user_database_manager_service.repository.user.role;

import com.example.user_database_manager_service.model.user.role.entity.UserRole;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRoleEntityRepositoryManager implements UserRoleEntityRepository {
    private final DSLContext dslContext;

    public UserRoleEntityRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<UserRole> getById(Long id) {
        return dslContext
                .select(Tables.USERS_ROLES.ID, Tables.USERS_ROLES.USER_UUID, Tables.USERS_ROLES.ROLE_ID)
                .from(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.ID.eq(id))
                .fetchOptionalInto(UserRole.class);
    }

    @Override
    public void save(UserRole userRole) {
        dslContext
                .insertInto(Tables.USERS_ROLES)
                .set(Tables.USERS_ROLES.USER_UUID, userRole.getUserUUID())
                .set(Tables.USERS_ROLES.ROLE_ID, userRole.getRoleId())
                .execute();
    }

    @Override
    public boolean existsById(Long id) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS_ROLES)
                                .where(Tables.USERS_ROLES.ID.eq(id))
                );
    }

    @Override
    public List<UserRole> getUserRolesByUserUUID(UUID userUUID) {
        return dslContext
                .select(Tables.USERS_ROLES.ID, Tables.USERS_ROLES.USER_UUID, Tables.USERS_ROLES.ROLE_ID)
                .from(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.eq(userUUID))
                .fetchInto(UserRole.class);
    }

    @Override
    public boolean existsByUserUUIDAndRoleId(UUID userUUID, Long roleId) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS_ROLES)
                                .where(Tables.USERS_ROLES.USER_UUID.eq(userUUID).and(Tables.USERS_ROLES.ROLE_ID.eq(roleId)))
                );
    }
}
