package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.repository.account.product.JooqAccountProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountProductEntityServiceManager extends AccountProductEntityService {
    public AccountProductEntityServiceManager(AccountProductRepository<AccountProduct> accountProductEntityRepository) {
        super(accountProductEntityRepository);
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        if (!existsBy(entity.getAccountUuid(), entity.getProductUrl())) {
            return accountProductEntityRepository.save(entity);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }
}
