package com.example.user_database_manager_service.service.account;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.model.account.entity.Account;
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
    public Account findByUUID(UUID uuid) {
        return accountEntityRepository.getByUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public Account findByUserUUID(UUID uuid) {
        return accountEntityRepository.getByUserUUID(uuid).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public void save(Account account) {
        if(!existsById(account.getId()) && !existsByUserUUID(account.getUuid())) {
            accountEntityRepository.save(account);
        } else throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public Account findById(Long id) {
        return accountEntityRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsByUserUUID(UUID uuid) {
        return accountEntityRepository.existsByUserUUID(uuid);
    }

    @Override
    public boolean existsById(Long id) {
        return accountEntityRepository.existsById(id);
    }
}
