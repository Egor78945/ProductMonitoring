package com.example.user_database_manager_service.service.account;

import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.account.AccountRepository;

import java.util.UUID;

public abstract class AccountRepositoryService<A> implements AccountService<A> {
    protected final AccountRepository<A> accountProtoRepository;

    public AccountRepositoryService(AccountRepository<A> accountProtoRepository) {
        this.accountProtoRepository = accountProtoRepository;
    }

    @Override
    public A save(A account) {
        return accountProtoRepository.save(account);
    }

    @Override
    public A update(A entity) {
        return accountProtoRepository.update(entity);
    }

    @Override
    public A findByUUID(UUID uuid) {
        return accountProtoRepository.getByUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public A findByUserUUID(UUID uuid) {
        return accountProtoRepository.getByUserUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public A findById(Long id) {
        return accountProtoRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public A findMainByUserUuid(UUID uuid) {
        return accountProtoRepository.getMainByUserUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public A findByAccountName(String accountName) {
        return accountProtoRepository.getByAccountName(accountName).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteById(Long id) {
        accountProtoRepository.deleteById(id);
    }

    @Override
    public void deleteByAccountUuid(UUID accountUuid) {
        accountProtoRepository.deleteByAccountUuid(accountUuid);
    }

    @Override
    public void deleteByUserUuid(UUID uuid) {
        accountProtoRepository.deleteByUserUuid(uuid);
    }

    @Override
    public boolean existsByUserUUID(UUID uuid) {
        return accountProtoRepository.existsByUserUUID(uuid);
    }

    @Override
    public boolean existsByName(String name) {
        return accountProtoRepository.existsByName(name);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return accountProtoRepository.existsByUUID(uuid);
    }

    @Override
    public boolean existsById(Long id) {
        return accountProtoRepository.existsById(id);
    }
}
