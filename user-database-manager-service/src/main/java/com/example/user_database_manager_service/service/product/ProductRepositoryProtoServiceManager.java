package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.product.ProductRepository;
import com.example.user_database_manager_service.service.product.common.CommonProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProductRepositoryProtoServiceManager extends ProductRepositoryProtoService {
    public ProductRepositoryProtoServiceManager(ProductRepository<UserProtoConfiguration.ProductMessage> productRepository, CommonProductService commonProductService) {
        super(productRepository, commonProductService);
    }
}
