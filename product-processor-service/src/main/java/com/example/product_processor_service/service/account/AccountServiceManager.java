package com.example.product_processor_service.service.account;

import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.repository.account.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceManager implements AccountService<Account> {
    private final AccountRepository<Account> accountRepository;

    public AccountServiceManager(AccountRepository<Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return accountRepository.existsByUuid(uuid);
    }

    @Override
    public Optional<Account> getByUserEmail(String email) {
        return accountRepository.findByUserEmail(email);
    }
}
