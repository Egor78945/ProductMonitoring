package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccountProductRepositoryEntityServiceManager extends AccountProductRepositoryEntityService {
    public AccountProductRepositoryEntityServiceManager(AccountProductRepository<AccountProduct> accountProductRepository, CommonAccountService commonAccountService, CommonAccountProductService commonAccountProductService) {
        super(accountProductRepository, commonAccountService, commonAccountProductService);
    }
}
