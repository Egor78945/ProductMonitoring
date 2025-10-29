package com.example.user_database_manager_service.repository.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class JooqUserRoleProtoRepository extends JooqUserRoleRepository {
    public JooqUserRoleProtoRepository(DSLContext dslContext) {
        super(dslContext);
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
}
