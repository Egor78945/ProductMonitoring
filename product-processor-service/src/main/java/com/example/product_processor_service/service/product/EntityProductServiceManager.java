package com.example.product_processor_service.service.product;

import com.example.product_processor_service.configuration.product.environment.ProductEnvironment;
import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.product.ProductRepository;
import org.jooq.DatePart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntityProductServiceManager implements EntityProductService<Product> {
    private final ProductRepository<Product> productRepository;
    private final ProductEnvironment productEnvironment;

    public EntityProductServiceManager(ProductRepository<Product> productRepository, ProductEnvironment productEnvironment) {
        this.productRepository = productRepository;
        this.productEnvironment = productEnvironment;
    }

    @Override
    public void save(Product product) {
        if(!existsByUrl(product.getUrl())) {
            productRepository.save(product);
        } else {
            throw new AlreadyExistsException("product already exists");
        }
    }

    @Override
    public Product getByUrl(String url) {
        return productRepository.getByUrl(url).orElseThrow(() -> new NotFoundException(String.format("product not found: %s", url)));
    }

    @Override
    public List<Product> getAllExpired(int limit) {
        return productRepository.getAllExpired(limit, DatePart.SECOND, productEnvironment.getPRODUCT_READ_COUNT());
    }

    @Override
    public boolean existsByUrl(String url) {
        return productRepository.existsByUrl(url);
    }

    @Override
    public List<Product> getAllByAccountUuid(UUID accountUuid, int page) {
        return productRepository.getAllByAccountUuid(accountUuid, page, productEnvironment.getPRODUCT_READ_COUNT());
    }
}
