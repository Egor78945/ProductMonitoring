package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.model.product.entity.Product;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EntityProductRepositoryManager extends EntityProductRepository<Product> {
    public EntityProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public void save(Product product) {
        dslContext
                .insertInto(Tables.PRODUCT)
                .set(Tables.PRODUCT.URL, product.getUrl())
                .set(Tables.PRODUCT.NAME, product.getName())
                .set(Tables.PRODUCT.ACTUAL_PRICE, product.getActualPrice())
                .set(Tables.PRODUCT.PAST_PRICE, product.getPastPrice())
                .set(Tables.PRODUCT.UPDATED_AT, product.getUpdatedAt().toLocalDateTime())
                .execute();
    }

    @Override
    public void saveAll(List<Product> products) {
        for (Product product : products) {
            save(product);
        }
    }

    @Override
    public Optional<Product> getByUrl(String url) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(Tables.PRODUCT.URL.eq(url))
                .fetchOptionalInto(Product.class);
    }

    @Override
    public List<Product> getAllExpired(int limit, DatePart datePart, int count) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(DSL.extract(DSL.currentTimestamp().minus(Tables.PRODUCT.UPDATED_AT), datePart).greaterThan(limit))
                .limit(count)
                .fetchInto(Product.class);
    }

    @Override
    public List<Product> getAllByAccountUuid(UUID uuid, int page, int pageSize) {
        return dslContext
                .selectFrom(Tables.PRODUCT.join(Tables.ACCOUNT_PRODUCTS).on(Tables.PRODUCT.URL.eq(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL)))
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(uuid))
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
}
