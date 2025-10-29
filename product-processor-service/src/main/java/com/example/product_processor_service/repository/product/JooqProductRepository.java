package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.util.function.Scrypt;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqProductRepository<P> extends ProductRepository<P> {
    protected final DSLContext dslContext;

    public JooqProductRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean existsByUrl(String url) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.PRODUCT)
                                .where(Tables.PRODUCT.URL.eq(url))
                );
    }

    public boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl) {
        return dslContext
                .fetchExists(dslContext
                        .selectOne()
                        .from(Tables.ACCOUNT_PRODUCTS)
                        .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid).and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl))));
    }

    @Override
    public void transactional(Scrypt scrypt) {
        dslContext.transaction(scrypt::execute);
    }
}
