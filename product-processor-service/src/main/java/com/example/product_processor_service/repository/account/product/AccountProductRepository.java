package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class AccountProductRepository {
    protected final DSLContext dslContext;

    public AccountProductRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public abstract void save(AccountProduct product);

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
