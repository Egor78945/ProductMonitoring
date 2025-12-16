package com.example.user_database_manager_service.service.user.common;

import com.example.user_database_manager_service.repository.user.common.CommonUserRepository;
import com.example.user_database_manager_service.service.user.operation.delete.DeleteUserService;
import org.springframework.stereotype.Service;

@Service
public class CommonUserRepositoryServiceManager extends CommonUserRepositoryService {
    public CommonUserRepositoryServiceManager(CommonUserRepository commonUserRepository, DeleteUserService deleteUserService) {
        super(commonUserRepository, deleteUserService);
    }
}
