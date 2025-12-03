package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.product.ProductRepository;

import java.net.URI;

public abstract class ProductRepositoryProtoService extends ProductRepositoryService<UserProtoConfiguration.ProductMessage> {
    public ProductRepositoryProtoService(ProductRepository<UserProtoConfiguration.ProductMessage> productRepository) {
        super(productRepository);
    }

    @Override
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage entity) {
        if(!existsByUrl(URI.create(entity.getUrl()))) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("Product is already exists by url: %s", entity));
    }

    @Override
    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage entity) {
        if(existsByUrl(URI.create(entity.getUrl()))) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("Product is not found by url: %s", entity));
    }
}
