package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductEntityService implements AccountProductService<AccountProduct> {
    protected final AccountProductRepository<AccountProduct> accountProductEntityRepository;

    public AccountProductEntityService(AccountProductRepository<AccountProduct> accountProductEntityRepository) {
        this.accountProductEntityRepository = accountProductEntityRepository;
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        return accountProductEntityRepository.save(entity);
    }

    @Override
    public AccountProduct update(AccountProduct entity) {
        return accountProductEntityRepository.update(entity);
    }

    @Override
    public void delete(AccountProduct entity) {
        accountProductEntityRepository.delete(entity);
    }

    @Override
    public void deleteAllByAccountUuid(UUID accountUuid) {
        accountProductEntityRepository.deleteAllByAccountUuid(accountUuid);
    }

    @Override
    public void deleteAllByProductUrl(URI productUrl) {
        accountProductEntityRepository.deleteAllByProductUrl(productUrl);
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return accountProductEntityRepository.existsBy(accountUuid, productUrl);
    }

    @Override
    public boolean existsByAccountUuid(UUID accountUuid) {
        return accountProductEntityRepository.existsByAccountUuid(accountUuid);
    }

    @Override
    public boolean existsByProductUrl(URI productUrl) {
        return accountProductEntityRepository.existsByProductUrl(productUrl);
    }
}
