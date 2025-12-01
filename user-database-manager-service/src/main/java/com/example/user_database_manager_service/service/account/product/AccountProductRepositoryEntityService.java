package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductRepositoryEntityService implements AccountProductService<AccountProduct> {
    protected final AccountProductRepository<AccountProduct> accountProductRepository;

    public AccountProductRepositoryEntityService(AccountProductRepository<AccountProduct> accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        return accountProductRepository.save(entity);
    }

    @Override
    public AccountProduct update(AccountProduct entity) {
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
