package com.example.product_processor_service.service.product;

import com.example.product_processor_service.configuration.product.environment.ProductEnvironment;
import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.product.ProductRepository;
import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DatePart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntityProductServiceManager implements EntityProductService {
    private final ProductRepository<Product> productRepository;
    private final ProductEnvironment productEnvironment;

    public EntityProductServiceManager(ProductRepository<Product> productRepository, ProductEnvironment productEnvironment) {
        this.productRepository = productRepository;
        this.productEnvironment = productEnvironment;
    }

    @Override
    public Product save(Product product) {
        if (!existsByUrl(product.getUrl())) {
            return productRepository.save(product);
        }
        throw new AlreadyExistsException(String.format("product with url %s already exists", product.getUrl()));
    }

    @Override
    public Product update(Product product) {
        if (productRepository.existsByUrl(product.getUrl())) {
            return productRepository.update(product);
        }
        throw new NotFoundException(String.format("product not found with url %s", product.getUrl()));
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
    public void transactional(Scrypt scrypt) {
        System.out.println("in service transactional");
        productRepository.transactional(scrypt);
    }

    @Override
    public List<Product> getAllByAccountUuid(UUID accountUuid, int page) {
        return productRepository.getAllByAccountUuid(accountUuid, page, productEnvironment.getPRODUCT_READ_COUNT());
    }
}
