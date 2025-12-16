package com.example.user_database_manager_service.service.user.common;

import com.example.user_database_manager_service.repository.user.common.CommonUserRepository;
import com.example.user_database_manager_service.service.user.operation.delete.DeleteUserService;

import java.util.UUID;

public abstract class CommonUserRepositoryService implements CommonUserService {
    protected final CommonUserRepository commonUserRepository;
    protected final DeleteUserService deleteUserService;

    public CommonUserRepositoryService(CommonUserRepository commonUserRepository, DeleteUserService deleteUserService) {
        this.commonUserRepository = commonUserRepository;
        this.deleteUserService = deleteUserService;
    }

    @Override
    public void deleteById(Long id) {
        deleteUserService.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        deleteUserService.deleteByUuid(uuid);
    }

    @Override
    public void deleteByEmail(String email) {
        deleteUserService.deleteByEmail(email);
    }

    @Override
    public boolean existsBy(Long id, UUID uuid, String email) {
        return commonUserRepository.existsBy(id, uuid, email);
    }

    @Override
    public boolean existsById(Long id) {
        return commonUserRepository.existsById(id);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return commonUserRepository.existsByUUID(uuid);
    }

    @Override
    public boolean existsByEmail(String email) {
        return commonUserRepository.existsByEmail(email);
    }
}
