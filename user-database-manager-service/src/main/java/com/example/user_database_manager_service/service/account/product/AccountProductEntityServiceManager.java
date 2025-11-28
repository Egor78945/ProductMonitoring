package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountProductEntityServiceManager extends AccountProductEntityService{
    public AccountProductEntityServiceManager(AccountProductRepository<AccountProduct> accountProductRepository) {
        super(accountProductRepository);
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        if(!existsBy(entity.getAccountUuid(), entity.getProductUrl())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("account product is already exists: %s", entity));
    }

    @Override
    public AccountProduct update(AccountProduct entity) {
        if(existsBy(entity.getAccountUuid(), entity.getProductUrl())) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("account product is not found: %s", entity));
    }
}
