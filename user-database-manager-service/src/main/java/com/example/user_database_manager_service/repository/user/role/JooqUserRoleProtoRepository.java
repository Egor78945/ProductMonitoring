package com.example.user_database_manager_service.repository.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class JooqUserRoleProtoRepository extends UserRoleProtoRepository {
    protected DSLContext dslContext;

    public JooqUserRoleProtoRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
        return dslContext
                .insertInto(Tables.USERS_ROLES)
                .set(Tables.USERS_ROLES.USER_UUID, UUID.fromString(userRole.getUserUUID()))
                .set(Tables.USERS_ROLES.ROLE_ID, userRole.getRoleId())
                .returning()
                .fetchOne(UserRoleMapper::map);
    }

    public Optional<UserProtoConfiguration.UserRoleMessage> getById(Long id) {
        return dslContext
                .select(Tables.USERS_ROLES)
                .from(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.ID.eq(id))
                .fetchOptional(ur -> UserRoleMapper.map(ur.value1()));
    }

    public List<UserProtoConfiguration.UserRoleMessage> getUserRolesByUserUUID(UUID userUUID) {
        return dslContext
                .select(Tables.USERS_ROLES)
                .from(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.eq(userUUID))
                .fetch(ur -> UserRoleMapper.map(ur.value1()));
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
