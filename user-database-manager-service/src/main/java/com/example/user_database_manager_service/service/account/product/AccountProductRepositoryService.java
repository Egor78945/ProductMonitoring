package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;

public abstract class AccountProductRepositoryService<AP> implements AccountProductService<AP> {
    protected final AccountProductRepository<AP> accountProductRepository;

    public AccountProductRepositoryService(AccountProductRepository<AP> accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public AP save(AP entity) {
        return accountProductRepository.save(entity);
    }

    @Override
    public AP update(AP entity) {
        return accountProductRepository.update(entity);
    }
}
