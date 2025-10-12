package com.example.product_processor_service.service.product;

import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.product_processor_service.configuration.product.environment.ProductEnvironment;
import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.repository.product.ProductRepository;
import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DatePart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProtoProductServiceManager implements ProtoProductService {
    private final ProductRepository<ProductServiceProtoConfiguration.ProductMessage> productRepository;
    private final ProductEnvironment productEnvironment;

    public ProtoProductServiceManager(ProductRepository<ProductServiceProtoConfiguration.ProductMessage> productRepository, ProductEnvironment productEnvironment) {
        this.productRepository = productRepository;
        this.productEnvironment = productEnvironment;
    }

    @Override
    public ProductServiceProtoConfiguration.ProductMessage save(ProductServiceProtoConfiguration.ProductMessage product) {
        if (!existsByUrl(product.getUrl())) {
            return productRepository.save(product);
        }
        throw new AlreadyExistsException("product already exists");
    }

    @Override
    public ProductServiceProtoConfiguration.ProductMessage update(ProductServiceProtoConfiguration.ProductMessage product) {
        if (productRepository.existsByUrl(product.getUrl())) {
            return productRepository.update(product);
        }
        throw new NotFoundException(String.format("product not found with url %s", product.getUrl()));
    }

    @Override
    public ProductServiceProtoConfiguration.ProductMessage getByUrl(String url) {
        return productRepository.getByUrl(url).orElseThrow(() -> new NotFoundException("product not found"));
    }

    @Override
    public List<ProductServiceProtoConfiguration.ProductMessage> getAllExpired(int limit) {
        return productRepository.getAllExpired(limit, DatePart.SECOND, productEnvironment.getPRODUCT_READ_COUNT());
    }

    @Override
    public boolean existsByUrl(String url) {
        return productRepository.existsByUrl(url);
    }

    @Override
    public void transactional(Scrypt scrypt) {
        productRepository.transactional(scrypt);
    }

    @Override
    public List<ProductServiceProtoConfiguration.ProductMessage> getAllByAccountUuid(UUID accountUuid, int page) {
        return productRepository.getAllByAccountUuid(accountUuid, page, productEnvironment.getPRODUCT_READ_COUNT());
    }
}
