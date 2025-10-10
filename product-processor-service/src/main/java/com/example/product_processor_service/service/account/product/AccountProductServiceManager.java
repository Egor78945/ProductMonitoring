package com.example.product_processor_service.service.account.product;

import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.account.AccountRepository;
import com.example.product_processor_service.repository.account.product.AccountProductRepository;
import com.example.product_processor_service.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountProductServiceManager implements AccountProductService<AccountProduct> {
    private final AccountProductRepository accountProductRepository;
    private final ProductRepository<Product> productRepository;
    private final AccountRepository accountRepository;

    public AccountProductServiceManager(AccountProductRepository accountProductRepository, ProductRepository<Product> productRepository, AccountRepository accountRepository) {
        this.accountProductRepository = accountProductRepository;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(AccountProduct product) {
        if (accountRepository.existsByUuid(product.getAccountUuid()) && productRepository.existsByUrl(product.getUrl()) && !productRepository.existsByAccountUuidAndProductUrl(product.getAccountUuid(), product.getUrl())) {
            accountProductRepository.save(product);
        } else {
            throw new AlreadyExistsException("account or product is not exists or account already has the product");
        }
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return accountRepository.existsByUuid(uuid);
    }
}
