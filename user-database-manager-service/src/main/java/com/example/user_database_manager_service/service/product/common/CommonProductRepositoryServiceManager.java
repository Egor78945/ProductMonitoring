package com.example.user_database_manager_service.service.product.common;

import com.example.user_database_manager_service.repository.product.common.CommonProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonProductRepositoryServiceManager extends CommonProductRepositoryService {
    public CommonProductRepositoryServiceManager(CommonProductRepository commonProductRepository) {
        super(commonProductRepository);
    }
}
