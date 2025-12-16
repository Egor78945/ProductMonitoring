package com.example.user_database_manager_service.repository.product.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.net.URI;
import java.util.UUID;

public abstract class JooqCommonProductRepository implements CommonProductRepository {
    protected final DSLContext dslContext;

    public JooqCommonProductRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean existsByUrl(URI url) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.PRODUCT)
                                .where(Tables.PRODUCT.URL.eq(url.toString()))
                );
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return dslContext
                .fetchExists(dslContext
                        .selectOne()
                        .from(Tables.ACCOUNT_PRODUCTS)
                        .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid).and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl.toString()))));
    }

    @Override
    public void deleteByUrl(URI url) {
        dslContext
                .deleteFrom(Tables.PRODUCT)
                .where(Tables.PRODUCT.URL.eq(url.toString()))
                .execute();
    }
}
