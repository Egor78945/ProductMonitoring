package com.example.user_database_manager_service.service.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.role.RoleRepository;

public abstract class RoleRepositoryProtoService extends RoleRepositoryService<UserProtoConfiguration.RoleMessage> {
    public RoleRepositoryProtoService(RoleRepository<UserProtoConfiguration.RoleMessage> roleRepository) {
        super(roleRepository);
    }
}
