package com.example.user_database_manager_service.repository.account.product.common;

import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.net.URI;
import java.util.UUID;

public abstract class JooqCommonAccountProductRepository implements CommonAccountProductRepository{
    protected final DSLContext dslContext;

    public JooqCommonAccountProductRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void deleteByAccountUuidAndProductUri(UUID accountUuid, URI productUrl) {
        dslContext
                .deleteFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid)
                        .and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl.toString())))
                .execute();
    }

    @Override
    public void deleteAllByProductUrl(URI productUrl) {
        dslContext
                .deleteFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl.toString()))
                .execute();

    }

    @Override
    public void deleteAllByAccountUuid(UUID accountUuid) {
        dslContext
                .deleteFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid))
                .execute();
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        dslContext
                .deleteFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.in(
                        dslContext.select(Tables.ACCOUNT.UUID)
                                .from(Tables.ACCOUNT.join(Tables.USERS)
                                        .on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID)))
                                .where(Tables.USERS.UUID.eq(userUuid))
                ))
                .execute();
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        dslContext
                .deleteFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.in(
                        dslContext.select(Tables.ACCOUNT.UUID)
                                .from(Tables.ACCOUNT.join(Tables.USERS)
                                        .on(Tables.ACCOUNT.USER_UUID.eq(Tables.USERS.UUID)))
                                .where(Tables.USERS.EMAIL.eq(email))
                ))
                .execute();
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT_PRODUCTS)
                                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid)
                                        .and(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl.toString())))
                );
    }

    @Override
    public boolean existsByProductUrl(URI productUrl) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT_PRODUCTS)
                                .where(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(productUrl.toString()))
                );
    }

    @Override
    public boolean existsByAccountUuid(UUID accountUuid) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.ACCOUNT_PRODUCTS)
                                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid))
                );
    }
}
