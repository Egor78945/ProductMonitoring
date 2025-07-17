package com.example.user_database_manager_service.repository.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;
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
    public Optional<UserProtoConfiguration.UserRoleMessage> getById(Long id) {
        return dslContext
                .select(Tables.USERS_ROLES.ID, Tables.USERS_ROLES.USER_UUID, Tables.USERS_ROLES.ROLE_ID)
                .from(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.ID.eq(id))
                .fetchOptional(UserRoleMapper::mapTo);
    }

    @Override
    public void save(UserProtoConfiguration.UserRoleMessage userRole) {
        dslContext
                .insertInto(Tables.USERS_ROLES)
                .set(Tables.USERS_ROLES.USER_UUID, UUID.fromString(userRole.getUserUUID()))
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
    public List<UserProtoConfiguration.UserRoleMessage> getUserRolesByUserUUID(UUID userUUID) {
        return dslContext
                .select(Tables.USERS_ROLES.ID, Tables.USERS_ROLES.USER_UUID, Tables.USERS_ROLES.ROLE_ID)
                .from(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.eq(userUUID))
                .fetch(UserRoleMapper::mapTo);
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
