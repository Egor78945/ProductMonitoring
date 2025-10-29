package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.repository.account.product.JooqAccountProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountProductEntityServiceManager extends AccountProductEntityService{
    public AccountProductEntityServiceManager(JooqAccountProductRepository accountProductEntityRepository) {
        super(accountProductEntityRepository);
    }
}
