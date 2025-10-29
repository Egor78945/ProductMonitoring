package com.example.user_database_manager_service.repository.account.product;

import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.net.URI;
import java.util.UUID;

public abstract class JooqAccountProductEntityRepository extends JooqAccountProductRepository {
    public JooqAccountProductEntityRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public AccountProduct save(AccountProduct entity) {
        return dslContext
                .insertInto(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, entity.getAccountUuid())
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, entity.getProductUrl().toString())
                .returning()
                .fetchOneInto(AccountProduct.class);
    }
}
