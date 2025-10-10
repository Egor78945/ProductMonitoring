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
public class AccountProductRepositoryManager extends AccountProductRepository<AccountProduct> {
    private final DSLContext dslContext;
    private final ProductRepository<Product> productRepository;
    private final AccountRepository<Account> accountRepository;

    public AccountProductRepositoryManager(DSLContext dslContext, ProductRepository<Product> productRepository, AccountRepository<Account> accountRepository) {
        this.dslContext = dslContext;
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
    @Override
    public boolean existsByUrlAndUserEmail(String url, String userEmail) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT_PRODUCTS
                                        .join(Tables.ACCOUNT)
                                        .on(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(Tables.ACCOUNT.UUID))
                                        .join(Tables.USERS).on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID)))
                                .where(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(url)
                                        .and(Tables.USERS.EMAIL.eq(userEmail)))
                );
    }

    @Override
    public boolean existsByUrlAndAccountUuid(String url, UUID accountUuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT_PRODUCTS)
                                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid)
                                        .and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(url)))
                );
    }
}
