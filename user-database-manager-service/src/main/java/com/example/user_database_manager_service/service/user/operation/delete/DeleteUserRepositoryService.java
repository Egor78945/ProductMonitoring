package com.example.user_database_manager_service.service.user.operation.delete;

import com.example.user_database_manager_service.repository.user.common.CommonUserRepository;

import java.util.UUID;

public abstract class DeleteUserRepositoryService implements DeleteUserService {
    protected final CommonUserRepository commonUserRepository;

    public DeleteUserRepositoryService(CommonUserRepository commonUserRepository) {
        this.commonUserRepository = commonUserRepository;
    }

    @Override
    public void deleteById(Long id) {
        commonUserRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        commonUserRepository.deleteByUuid(uuid);
    }

    @Override
    public void deleteByEmail(String email) {
        commonUserRepository.deleteByEmail(email);
    }
}
