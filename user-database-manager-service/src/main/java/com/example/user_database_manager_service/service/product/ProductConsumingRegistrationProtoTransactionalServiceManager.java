package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.product.common.CommonProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductConsumingRegistrationProtoTransactionalServiceManager extends ProductConsumingRegistrationProtoService {
    public ProductConsumingRegistrationProtoTransactionalServiceManager(ProductService<UserProtoConfiguration.ProductMessage> productService,
                                                                           CommonProductService commonProductService,
                                                                           AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService,
                                                                           CommonAccountProductService commonAccountProductService,
                                                                           CommonAccountService commonAccountService) {
        super(productService, commonProductService, accountProductService, commonAccountProductService, commonAccountService);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void register(UserProtoConfiguration.ProductRegistrationMessage subject) {
        System.out.println("PRODUCT TRANSACTIONAL REGISTER");
        super.register(subject);
    }
}
