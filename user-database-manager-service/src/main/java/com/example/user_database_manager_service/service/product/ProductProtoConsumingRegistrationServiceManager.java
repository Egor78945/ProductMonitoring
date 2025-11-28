package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductProtoConsumingRegistrationServiceManager extends ProductProtoConsumingRegistrationService {
    protected ProductProtoConsumingRegistrationServiceManager(ProductService<UserProtoConfiguration.ProductMessage> productService, AccountProductService<AccountProduct> accountProductService, AccountService<UserProtoConfiguration.AccountMessage> accountService) {
        super(productService, accountProductService, accountService);
    }
}
