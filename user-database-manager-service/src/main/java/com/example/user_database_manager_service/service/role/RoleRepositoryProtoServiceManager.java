package com.example.user_database_manager_service.service.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.role.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleRepositoryProtoServiceManager extends RoleRepositoryProtoService{
    public RoleRepositoryProtoServiceManager(RoleRepository<UserProtoConfiguration.RoleMessage> roleRepository) {
        super(roleRepository);
    }
}
