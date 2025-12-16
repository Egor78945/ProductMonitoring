package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.product.common.CommonProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProductConsumingRegistrationProtoServiceManager extends ProductConsumingRegistrationProtoService {
    public ProductConsumingRegistrationProtoServiceManager(ProductService<UserProtoConfiguration.ProductMessage> productService, CommonProductService commonProductService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService, CommonAccountProductService commonAccountProductService, CommonAccountService commonAccountService) {
        super(productService, commonProductService, accountProductService, commonAccountProductService, commonAccountService);
    }
}
