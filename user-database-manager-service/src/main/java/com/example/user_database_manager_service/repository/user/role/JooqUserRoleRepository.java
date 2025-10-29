package com.example.user_database_manager_service.repository.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqUserRoleRepository extends UserRoleRepository<UserProtoConfiguration.UserRoleMessage> {
    protected final DSLContext dslContext;

    public JooqUserRoleRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean existsById(Long id) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS_ROLES)
                                .where(Tables.USERS_ROLES.ID.eq(id))
                );
    }

    public boolean existsBy(UUID userUUID, Long roleId) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.USERS_ROLES)
                                .where(Tables.USERS_ROLES.USER_UUID.eq(userUUID).and(Tables.USERS_ROLES.ROLE_ID.eq(roleId)))
                );
    }

    @Override
    public void delete(UserProtoConfiguration.UserRoleMessage entity) {
        dslContext
                .deleteFrom(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.eq(UUID.fromString(entity.getUserUUID()))
                        .and(Tables.USERS_ROLES.ROLE_ID.eq(entity.getRoleId())))
                .execute();
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        dslContext
                .deleteFrom(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.eq(userUuid))
                .execute();
    }

    @Override
    public void deleteAllByRoleId(Long roleId) {
        dslContext
                .deleteFrom(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.ROLE_ID.eq(roleId))
                .execute();
    }
}