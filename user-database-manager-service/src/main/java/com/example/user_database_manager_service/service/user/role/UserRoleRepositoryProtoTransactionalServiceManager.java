package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.role.common.CommonUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleRepositoryProtoTransactionalServiceManager extends UserRoleRepositoryProtoService {
    public UserRoleRepositoryProtoTransactionalServiceManager(UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository, CommonUserService commonUserService, CommonUserRoleService commonUserRoleService) {
        super(userRoleProtoRepository, commonUserService, commonUserRoleService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
        return super.save(userRole);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserRoleMessage update(UserProtoConfiguration.UserRoleMessage userRole) {
        return super.update(userRole);
    }
}
