package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.model.product.entity.Product;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryManager extends ProductRepository<Product> {
    private final DSLContext dslContext;

    public ProductRepositoryManager(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void save(Product product) {
        dslContext
                .insertInto(Tables.PRODUCT)
                .set(Tables.PRODUCT.URL, product.getUrl())
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
    public List<Product> getExpired(Timestamp now, int limit, DatePart datePart) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(DSL.extract(DSL.timestamp(now).minus(Tables.PRODUCT.UPDATED_AT), datePart).greaterThan(limit))
                .fetchInto(Product.class);
    }

    @Override
    public boolean existsByUrl(String url) {
        return dslContext
                .fetchExists(
                        dslContext.selectOne()
                                .from(Tables.PRODUCT)
                                .where(Tables.PRODUCT.URL.eq(url))
                );
    }
}
