package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ProductProtoServiceManager extends ProductProtoService{
    public ProductProtoServiceManager(ProductRepository<UserProtoConfiguration.ProductMessage> productRepository) {
        super(productRepository);
    }

    @Override
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage entity) {
        if(!existsByUrl(URI.create(entity.getUrl()))) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }
}
