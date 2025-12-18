package com.example.user_database_manager_service.repository.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.role.mapper.RoleMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class JooqRoleProtoRepository extends JooqRoleRepository<UserProtoConfiguration.RoleMessage> {
    public JooqRoleProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public List<UserProtoConfiguration.RoleMessage> findByUserEmail(String email) {
        return dslContext
                .select(Tables.USER_ROLE)
                .from(Tables.USERS.join(Tables.USERS_ROLES)
                        .on(Tables.USERS.UUID.eq(Tables.USERS_ROLES.USER_UUID))
                        .join(Tables.USER_ROLE)
                        .on(Tables.USER_ROLE.ID.eq(Tables.USERS_ROLES.ROLE_ID)))
                .where(Tables.USERS.EMAIL.eq(email))
                .fetch(r -> RoleMapper.mapTo(r.value1()));
    }
}
