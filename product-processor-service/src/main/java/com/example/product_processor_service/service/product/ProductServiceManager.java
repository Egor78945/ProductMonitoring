package com.example.product_processor_service.service.product;

import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.configuration.product.environment.ProductEnvironment;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.product.ProductRepository;
import org.jooq.DatePart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceManager implements ProductService<Product> {
    private final ProductRepository<Product> productRepository;
    private final ProductEnvironment productEnvironment;

    public ProductServiceManager(ProductRepository<Product> productRepository, ProductEnvironment productEnvironment) {
        this.productRepository = productRepository;
        this.productEnvironment = productEnvironment;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
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
}
