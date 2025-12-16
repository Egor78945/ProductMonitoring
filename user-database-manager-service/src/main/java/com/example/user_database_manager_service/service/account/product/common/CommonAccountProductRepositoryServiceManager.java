package com.example.user_database_manager_service.service.account.product.common;

import com.example.user_database_manager_service.repository.account.product.common.CommonAccountProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonAccountProductRepositoryServiceManager extends CommonAccountProductRepositoryService{
    public CommonAccountProductRepositoryServiceManager(CommonAccountProductRepository commonAccountProductRepository) {
        super(commonAccountProductRepository);
    }
}
