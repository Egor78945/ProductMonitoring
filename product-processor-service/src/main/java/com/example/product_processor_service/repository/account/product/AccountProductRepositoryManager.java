package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.product.entity.Product;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountProductRepositoryManager extends AccountProductRepository<Product> {
    private final DSLContext dslContext;

    public AccountProductRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Product> getAllByAccountUuid(UUID uuid, int page, int pageSize) {
        return dslContext.select(Tables.PRODUCT)
                .from(Tables.ACCOUNT
                        .join(Tables.ACCOUNT_PRODUCTS)
                        .on(Tables.ACCOUNT.UUID.eq(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID))
                        .join(Tables.PRODUCT)
                        .on(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(Tables.PRODUCT.URL)))
                .limit(pageSize)
                .offset((page - 1) * pageSize)
                .fetchInto(Product.class);

    }

    @Override
    public Optional<Product> getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl) {
        return dslContext
                .select(Tables.PRODUCT)
                .from(Tables.ACCOUNT
                        .join(Tables.ACCOUNT_PRODUCTS)
                        .on(Tables.ACCOUNT.UUID.eq(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID))
                        .join(Tables.PRODUCT)
                        .on(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(Tables.PRODUCT.URL)))
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid).and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl)))
                .fetchOptionalInto(Product.class);
    }

    @Override
    public boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl) {
        return dslContext
                .fetchExists(dslContext
                        .selectOne()
                        .from(Tables.ACCOUNT_PRODUCTS)
                        .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid).and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl))));
    }
}
