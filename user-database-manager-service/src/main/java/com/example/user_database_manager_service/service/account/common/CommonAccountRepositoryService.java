package com.example.user_database_manager_service.service.account.common;

import com.example.user_database_manager_service.repository.account.common.CommonAccountRepository;

import java.util.UUID;

public abstract class CommonAccountRepositoryService implements CommonAccountService {
    protected CommonAccountRepository commonAccountRepository;

    public CommonAccountRepositoryService(CommonAccountRepository commonAccountRepository) {
        this.commonAccountRepository = commonAccountRepository;
    }

    @Override
    public int getCountOfAccountsOfUserByUserUUID(UUID uuid) {
        return commonAccountRepository.getCountOfAccountsOfUserByUserUUID(uuid);
    }

    @Override
    public void deleteById(Long id) {
        commonAccountRepository.deleteById(id);
    }

    @Override
    public void deleteByAccountUuid(UUID accountUuid) {
        commonAccountRepository.deleteByAccountUuid(accountUuid);
    }

    @Override
    public void deleteByUserUuid(UUID uuid) {
        commonAccountRepository.deleteByUserUuid(uuid);
    }

    @Override
    public void deleteByUserEmail(String email) {
        commonAccountRepository.deleteByUserEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return commonAccountRepository.existsById(id);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return commonAccountRepository.existsByUUID(uuid);
    }

    @Override
    public boolean existsByUserUUID(UUID userUuuid) {
        return commonAccountRepository.existsByUserUUID(userUuuid);
    }

    @Override
    public boolean existsByName(String name) {
        return commonAccountRepository.existsByName(name);
    }
}
