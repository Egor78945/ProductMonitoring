package com.example.product_processor_service.service.account.product;

import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.account.product.AccountProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountProductServiceManager implements AccountProductService<Product> {
    private final AccountProductRepository<Product> accountProductRepository;

    public AccountProductServiceManager(AccountProductRepository<Product> accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public Product getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl) {
        return accountProductRepository.getByAccountUuidAndProductUrl(accountUuid, productUrl).orElseThrow(() -> new NotFoundException(String.format("product not found. product: %s, account: %s", productUrl, accountUuid)));
    }

    @Override
    public List<Product> getAllByAccountUuid(UUID accountUuid, int page, int size) {
        return accountProductRepository.getAllByAccountUuid(accountUuid, page, size);
    }

    @Override
    public boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl) {
        return accountProductRepository.existsByAccountUuidAndProductUrl(accountUuid, productUrl);
    }
}
