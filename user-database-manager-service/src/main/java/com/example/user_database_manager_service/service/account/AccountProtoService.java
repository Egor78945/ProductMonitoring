package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.account.AccountRepository;
import com.example.user_database_manager_service.repository.account.JooqAccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;

import java.util.UUID;

public abstract class AccountProtoService implements AccountService<UserProtoConfiguration.AccountMessage> {
    protected final AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository;
    protected final UserRepository<?> userRepository;

    public AccountProtoService(AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository, UserRepository<?> userRepository) {
        this.accountProtoRepository = accountProtoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage account) {
        return accountProtoRepository.save(account);
    }

    @Override
    public UserProtoConfiguration.AccountMessage update(UserProtoConfiguration.AccountMessage entity) {
        return accountProtoRepository.update(entity);
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
    public UserProtoConfiguration.AccountMessage findByUUID(UUID uuid) {
        return accountProtoRepository.getByUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.AccountMessage findByUserUUID(UUID uuid) {
        return accountProtoRepository.getByUserUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.AccountMessage findById(Long id) {
        return accountProtoRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
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
