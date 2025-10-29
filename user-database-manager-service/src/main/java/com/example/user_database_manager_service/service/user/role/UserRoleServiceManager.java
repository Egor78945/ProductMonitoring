package com.example.user_database_manager_service.service.user.role;

import com.example.user_database_manager_service.repository.user.role.JooqUserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceManager extends UserRoleProtoService {
    public UserRoleServiceManager(JooqUserRoleRepository userRoleProtoRepository) {
        super(userRoleProtoRepository);
    }
}
