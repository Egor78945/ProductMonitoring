package com.example.user_database_manager_service.service.user.role;

import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;

import java.util.List;
import java.util.UUID;

public abstract class UserRoleRepositoryService<U> implements UserRoleService<U> {
    protected final UserRoleRepository<U> userRoleRepository;

    public UserRoleRepositoryService(UserRoleRepository<U> userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public U save(U userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public U update(U entity) {
        return userRoleRepository.update(entity);
    }

    @Override
    public U findById(Long id) {
        return userRoleRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public List<U> findByUserUUID(UUID userUUID) {
        return userRoleRepository.getUserRolesByUserUUID(userUUID);
    }
}
