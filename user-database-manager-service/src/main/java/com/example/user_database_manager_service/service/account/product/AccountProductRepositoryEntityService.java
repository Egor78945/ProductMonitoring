package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.service.account.AccountService;

public abstract class AccountProductRepositoryEntityService extends AccountProductRepositoryService<AccountProduct> {
    protected final AccountService<?> accountService;

    public AccountProductRepositoryEntityService(AccountProductRepository<AccountProduct> accountProductRepository, AccountService<?> accountService) {
        super(accountProductRepository);
        this.accountService = accountService;
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        if (accountService.existsByUUID(entity.getAccountUuid()) && !existsBy(entity.getAccountUuid(), entity.getProductUrl())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("AccountProduct by account uuid and product url is already exists: %s", entity));
    }

    @Override
    public AccountProduct update(AccountProduct entity) {
        if (accountService.existsByUUID(entity.getAccountUuid()) && existsBy(entity.getAccountUuid(), entity.getProductUrl())) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("AccountProduct is not found by account uuid and product url: %s", entity));
    }
}
