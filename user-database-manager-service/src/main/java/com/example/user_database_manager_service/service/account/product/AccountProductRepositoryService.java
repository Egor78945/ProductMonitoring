package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductRepositoryService<AP> implements AccountProductService<AP> {
    protected final AccountProductRepository<AP> accountProductRepository;

    protected AccountProductRepositoryService(AccountProductRepository<AP> accountProductRepository) {
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

    @Override
    public void deleteAllByAccountUuid(UUID accountUuid) {
        accountProductRepository.deleteAllByAccountUuid(accountUuid);
    }

    @Override
    public void deleteAllByProductUrl(URI productUrl) {
        accountProductRepository.deleteAllByProductUrl(productUrl);
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        accountProductRepository.deleteAllByUserUuid(userUuid);
    }

    @Override
    public void deleteByAccountUuidAndProductUri(UUID accountUuid, URI productUrl) {
        accountProductRepository.deleteByAccountUuidAndProductUri(accountUuid, productUrl);
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return accountProductRepository.existsBy(accountUuid, productUrl);
    }

    @Override
    public boolean existsByAccountUuid(UUID accountUuid) {
        return accountProductRepository.existsByAccountUuid(accountUuid);
    }

    @Override
    public boolean existsByProductUrl(URI productUrl) {
        return accountProductRepository.existsByProductUrl(productUrl);
    }
}
