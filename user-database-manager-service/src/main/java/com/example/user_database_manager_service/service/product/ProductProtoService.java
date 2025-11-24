package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.product.ProductRepository;
import org.jooq.DatePart;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ProductProtoService implements ProductService<UserProtoConfiguration.ProductMessage> {
    protected final ProductRepository<UserProtoConfiguration.ProductMessage> productRepository;

    public ProductProtoService(ProductRepository<UserProtoConfiguration.ProductMessage> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage entity) {
        return productRepository.save(entity);
    }

    @Override
    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage entity) {
        return productRepository.update(entity);
    }

    @Override
    public Optional<UserProtoConfiguration.ProductMessage> findByUrl(URI url) {
        return productRepository.findByUrl(url);
    }

    @Override
    public Optional<UserProtoConfiguration.ProductMessage> findBy(UUID accountUuid, URI productUrl) {
        return productRepository.findBy(accountUuid, productUrl);
    }

    @Override
    public List<UserProtoConfiguration.ProductMessage> findAllExpired(int timeLimit, DatePart datePart, int count) {
        return productRepository.findAllExpired(timeLimit, datePart, count);
    }

    @Override
    public List<UserProtoConfiguration.ProductMessage> findAllBy(UUID accountUuid, int page, int count) {
        return productRepository.findAllBy(accountUuid, page, count);
    }

    @Override
    public boolean existsByUrl(URI url) {
        return productRepository.existsByUrl(url);
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return productRepository.existsBy(accountUuid, productUrl);
    }
}
