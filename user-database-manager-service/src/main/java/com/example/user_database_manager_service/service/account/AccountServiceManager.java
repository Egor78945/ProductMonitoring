package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.account.AccountEntityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceManager implements AccountService {
    private final AccountEntityRepository accountEntityRepository;

    public AccountServiceManager(AccountEntityRepository accountEntityRepository) {
        this.accountEntityRepository = accountEntityRepository;
    }

    @Override
    public UserProtoConfiguration.AccountMessage findByUUID(UUID uuid) {
        return accountEntityRepository.getByUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.AccountMessage findByUserUUID(UUID uuid) {
        return accountEntityRepository.getByUserUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public void save(UserProtoConfiguration.AccountMessage account) {
        if(!existsById(account.getId()) && !existsByUUID(UUID.fromString(account.getUuid())) && !existsByUserUUID(UUID.fromString(account.getUuid()))) {
            accountEntityRepository.save(account);
        } else throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.AccountMessage findById(Long id) {
        return accountEntityRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsByUserUUID(UUID uuid) {
        return accountEntityRepository.existsByUserUUID(uuid);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return accountEntityRepository.existsByUserUUID(uuid);
    }

    @Override
    public boolean existsById(Long id) {
        return accountEntityRepository.existsById(id);
    }

    public boolean canBeSaved(UserProtoConfiguration.AccountMessage account) {
        return account.getId() == 0 && !existsByUUID(UUID.fromString(account.getUuid())) && !existsByUserUUID(UUID.fromString(account.getUserUuid()));
    }
}
