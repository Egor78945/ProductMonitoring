package com.example.user_database_manager_service.service.product;

import com.example.user_database_manager_service.repository.product.ProductRepository;
import org.jooq.DatePart;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ProductRepositoryService<P> implements ProductService<P> {
    protected final ProductRepository<P> productRepository;

    public ProductRepositoryService(ProductRepository<P> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public P save(P entity) {
        return productRepository.save(entity);
    }

    @Override
    public P update(P entity) {
        return productRepository.update(entity);
    }

    @Override
    public Optional<P> findByUrl(URI url) {
        return productRepository.findByUrl(url);
    }

    @Override
    public Optional<P> findBy(UUID accountUuid, URI productUrl) {
        return productRepository.findBy(accountUuid, productUrl);
    }

    @Override
    public List<P> findAllExpired(int timeLimit, DatePart datePart, int count) {
        return productRepository.findAllExpired(timeLimit, datePart, count);
    }

    @Override
    public List<P> findAllBy(UUID accountUuid, int page, int count) {
        return productRepository.findAllBy(accountUuid, page, count);
    }
}
