package com.example.product_processor_service.service.product;

import com.example.product_processor_service.configuration.product.environment.ProductEnvironment;
import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.account.AccountRepository;
import com.example.product_processor_service.repository.account.product.AccountProductRepository;
import com.example.product_processor_service.repository.product.ProductRepository;
import org.jooq.DatePart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntityProductServiceManager implements EntityProductService<Product> {
    private final ProductRepository<Product> productRepository;
    private final AccountProductRepository accountProductRepository;
    private final AccountRepository accountRepository;
    private final ProductEnvironment productEnvironment;

    public EntityProductServiceManager(ProductRepository<Product> productRepository, AccountProductRepository accountProductRepository, AccountRepository accountRepository, ProductEnvironment productEnvironment) {
        this.productRepository = productRepository;
        this.accountProductRepository = accountProductRepository;
        this.accountRepository = accountRepository;
        this.productEnvironment = productEnvironment;
    }

    @Override
    public void save(Product product) {
        if (!existsByUrl(product.getUrl())) {
            productRepository.save(product);
        } else {
            throw new AlreadyExistsException("product already exists");
        }
    }

    @Override
    public void register(Product product, UUID accountUuid) {
        if (accountRepository.existsByUuid(accountUuid)) {
            if (!productRepository.existsByUrl(product.getUrl())) {
                save(product);
            }
            if(!accountProductRepository.existsByUrlAndAccountUuid(product.getUrl(), accountUuid)){
                accountProductRepository.save(new AccountProduct(product.getUrl(), accountUuid));
            }
        } else {
            throw new NotFoundException("account not found");
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
    public boolean existsByUrlAndUserEmail(String url, String userEmail) {
        return false;
    }

    @Override
    public List<Product> getAllByAccountUuid(UUID accountUuid, int page) {
        return productRepository.getAllByAccountUuid(accountUuid, page, productEnvironment.getPRODUCT_READ_COUNT());
    }
}
