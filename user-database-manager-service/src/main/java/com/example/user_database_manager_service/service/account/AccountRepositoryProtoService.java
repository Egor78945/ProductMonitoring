package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.repository.account.AccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;

import java.util.UUID;

public abstract class AccountRepositoryProtoService extends AccountRepositoryService<UserProtoConfiguration.AccountMessage> {
    protected final UserRepository<?> userRepository;

    public AccountRepositoryProtoService(AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository, UserRepository<?> userRepository) {
        super(accountProtoRepository);
        this.userRepository = userRepository;
    }

    @Override
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage account) {
        if (userRepository.existsByUUID(UUID.fromString(account.getUserUuid())) && !accountProtoRepository.existsByName(account.getName()) && accountProtoRepository.getCountOfAccountsOfUserByUserUUID(UUID.fromString(account.getUserUuid())) < 3) {
            return super.save(account);
        }
        throw new ProcessingException(String.format("User not found or count of accounts of user is exceeded: %s", account));
    }

    @Override
    public UserProtoConfiguration.AccountMessage update(UserProtoConfiguration.AccountMessage account) {
        if (existsByUUID(UUID.fromString(account.getUuid()))) {
            return super.update(account);
        }
        throw new NotFoundException(String.format("Account is not found by uuid: %s", account.getUuid()));
    }
}
