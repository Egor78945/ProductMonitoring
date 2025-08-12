package com.example.product_processor_service.service.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.repository.account.product.AccountProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountProductServiceManager implements AccountProductService<AccountProduct> {
    private final AccountProductRepository<AccountProduct> accountProductRepository;

    public AccountProductServiceManager(AccountProductRepository<AccountProduct> accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public void save(AccountProduct product) {
        accountProductRepository.save(product);
    }
}
