package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.product.ProductRepository;
import com.example.user_database_manager_service.service.product.common.CommonProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductRepositoryProtoTransactionalServiceManager extends ProductRepositoryProtoService {
    public ProductRepositoryProtoTransactionalServiceManager(ProductRepository<UserProtoConfiguration.ProductMessage> productRepository, CommonProductService commonProductService) {
        super(productRepository, commonProductService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage entity) {
        return super.update(entity);
    }
}
