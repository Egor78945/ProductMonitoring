package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.product.ProductRepository;
import com.example.user_database_manager_service.service.product.common.CommonProductService;

import java.net.URI;

public abstract class ProductRepositoryProtoService extends ProductRepositoryService<UserProtoConfiguration.ProductMessage> {
    protected final CommonProductService commonProductService;

    public ProductRepositoryProtoService(ProductRepository<UserProtoConfiguration.ProductMessage> productRepository, CommonProductService commonProductService) {
        super(productRepository);
        this.commonProductService = commonProductService;
    }

    @Override
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage entity) {
        if (!commonProductService.existsByUrl(URI.create(entity.getUrl()))) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("Product is already exists by url: %s", entity));
    }

    @Override
    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage entity) {
        if (commonProductService.existsByUrl(URI.create(entity.getUrl()))) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("Product is not found by url: %s", entity));
    }
}
