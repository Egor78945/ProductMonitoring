package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProductConsumingRegistrationProtoServiceManager extends ProductConsumingRegistrationProtoService {
    protected ProductConsumingRegistrationProtoServiceManager(ProductService<UserProtoConfiguration.ProductMessage> productService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService, AccountService<?> accountService) {
        super(productService, accountProductService, accountService);
    }
}
