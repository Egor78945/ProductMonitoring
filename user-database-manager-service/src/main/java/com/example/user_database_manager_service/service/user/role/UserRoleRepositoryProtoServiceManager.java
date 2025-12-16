package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.role.common.CommonUserRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserRoleRepositoryProtoServiceManager extends UserRoleRepositoryProtoService {
    public UserRoleRepositoryProtoServiceManager(UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository, CommonUserService commonUserService, CommonUserRoleService commonUserRoleService) {
        super(userRoleProtoRepository, commonUserService, commonUserRoleService);
    }
}
