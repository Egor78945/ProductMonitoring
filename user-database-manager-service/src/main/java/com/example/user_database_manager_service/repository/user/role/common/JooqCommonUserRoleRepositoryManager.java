package com.example.user_database_manager_service.repository.user.role.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonUserRoleRepositoryManager extends JooqCommonUserRoleRepository{
    public JooqCommonUserRoleRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
