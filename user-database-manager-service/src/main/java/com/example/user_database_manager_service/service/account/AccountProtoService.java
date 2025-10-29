package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.account.JooqAccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;

import java.util.UUID;

public abstract class AccountProtoService implements AccountService<UserProtoConfiguration.AccountMessage> {
    protected final JooqAccountRepository accountProtoRepository;
    protected final UserRepository<?> userRepository;

    public AccountProtoService(JooqAccountRepository accountProtoRepository, UserRepository<?> userRepository) {
        this.accountProtoRepository = accountProtoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage account) {
        account.toBuilder().setName(account.getName().toLowerCase()).build();
        if (!existsByName(account.getName()) && userRepository.existsByUUID(UUID.fromString(account.getUserUuid()))) {
            return accountProtoRepository.save(account);
        } else throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public void delete(UserProtoConfiguration.AccountMessage account) {
        accountProtoRepository.delete(account);
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
