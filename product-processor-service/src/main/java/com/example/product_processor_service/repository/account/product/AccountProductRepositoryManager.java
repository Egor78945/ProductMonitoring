package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.exception.AlreadyExistsException;
import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.account.AccountRepository;
import com.example.product_processor_service.repository.product.ProductRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountProductRepositoryManager extends AccountProductRepository {
    private final ProductRepository<Product> productRepository;
    private final AccountRepository accountRepository;

    public AccountProductRepositoryManager(DSLContext dslContext, ProductRepository<Product> productRepository, AccountRepository accountRepository) {
        super(dslContext);
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void save(AccountProduct product) {
        if(!productRepository.existsByAccountUuidAndProductUrl(product.getAccountUuid(), product.getUrl()) && accountRepository.existsByUuid(product.getAccountUuid())) {
            dslContext
                    .insertInto(Tables.ACCOUNT_PRODUCTS)
                    .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, product.getAccountUuid())
                    .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, product.getUrl())
                    .execute();

        } else {
            throw new AlreadyExistsException("account is not exists or already has the product");
        }
    }
}
