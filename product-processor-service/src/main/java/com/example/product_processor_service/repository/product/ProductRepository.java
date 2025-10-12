package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.jooq.DatePart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ProductRepository<P> extends JooqRepository<P> {
    public ProductRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public abstract Optional<P> getByUrl(String url);

    public abstract Optional<P> getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);

    public abstract List<P> getAllExpired(int timeLimit, DatePart datePart, int count);

    public abstract List<P> getAllByAccountUuid(UUID uuid, int page, int pageSize);

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
}
