package com.example.user_database_manager_service.service.user.role.common;

import com.example.user_database_manager_service.repository.user.role.common.CommonUserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonUserRoleRepositoryServiceManager extends CommonUserRoleRepositoryService{
    public CommonUserRoleRepositoryServiceManager(CommonUserRoleRepository commonUserRoleRepository) {
        super(commonUserRoleRepository);
    }
}
