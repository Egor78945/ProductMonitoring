package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;
import java.util.UUID;

public abstract class AccountProductRepository extends JooqRepository<AccountProduct> {
    public AccountProductRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public AccountProduct save(AccountProduct accountProduct) {
        return dslContext
                .insertInto(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, accountProduct.getAccountUuid())
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, accountProduct.getUrl())
                .returning()
                .fetchOneInto(AccountProduct.class);

    }

    @Override
    public AccountProduct update(AccountProduct accountProduct) {
        return dslContext
                .update(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, accountProduct.getAccountUuid())
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, accountProduct.getUrl())
                .returning()
                .fetchOneInto(AccountProduct.class);
    }

    @Override
    public void saveAll(List<AccountProduct> products) {
        for (AccountProduct ap : products) {
            save(ap);
        }
    }

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

    public List<AccountProduct> findAllByUrl(String url) {
        return dslContext
                .selectFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(url))
                .fetchInto(AccountProduct.class);
    }
}
