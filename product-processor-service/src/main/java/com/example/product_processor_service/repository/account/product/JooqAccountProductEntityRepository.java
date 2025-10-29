package com.example.product_processor_service.repository.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class JooqAccountProductEntityRepository extends JooqAccountProductRepository<AccountProduct> {
    protected JooqAccountProductEntityRepository(DSLContext dslContext) {
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
    public List<AccountProduct> findAllByUrl(String url) {
        return dslContext
                .selectFrom(Tables.ACCOUNT_PRODUCTS)
                .where(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(url))
                .fetchInto(AccountProduct.class);
    }
}
