package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.service.account.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountProductRepositoryEntityTransactionalServiceManager extends AccountProductRepositoryEntityService{
    public AccountProductRepositoryEntityTransactionalServiceManager(AccountProductRepository<AccountProduct> accountProductRepository, AccountService<?> accountService) {
        super(accountProductRepository, accountService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountProduct save(AccountProduct entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountProduct update(AccountProduct entity) {
        return super.update(entity);
    }
}
