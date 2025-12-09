package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductConsumingRegistrationProtoTransactionalServiceManager extends ProductConsumingRegistrationProtoService {
    protected ProductConsumingRegistrationProtoTransactionalServiceManager(@Qualifier("productRepositoryProtoTransactionalServiceManager") ProductService<UserProtoConfiguration.ProductMessage> productService,
                                                                           @Qualifier("accountProductRepositoryProtoTransactionalServiceManager") AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService,
                                                                           AccountService<?> accountService) {
        super(productService, accountProductService, accountService);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void register(UserProtoConfiguration.ProductRegistrationMessage subject) {
        System.out.println("PRODUCT TRANSACTIONAL REGISTER");
        super.register(subject);
    }
}
