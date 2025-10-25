package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductEntityRepository;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductEntityService implements AccountProductService<AccountProduct> {
    protected final AccountProductEntityRepository accountProductEntityRepository;

    public AccountProductEntityService(AccountProductEntityRepository accountProductEntityRepository) {
        this.accountProductEntityRepository = accountProductEntityRepository;
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        if (!existsBy(entity.getAccountUuid(), entity.getProductUrl())) {
            return accountProductEntityRepository.save(entity);
        } else throw new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage());
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
