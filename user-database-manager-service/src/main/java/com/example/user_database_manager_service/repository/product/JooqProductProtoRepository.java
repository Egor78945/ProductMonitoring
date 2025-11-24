package com.example.user_database_manager_service.repository.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.product.mapper.ProductMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.impl.DSL;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class JooqProductProtoRepository extends JooqProductRepository<UserProtoConfiguration.ProductMessage>{
    public JooqProductProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage product) {
        return dslContext
                .insertInto(Tables.PRODUCT)
                .set(Tables.PRODUCT.URL, product.getUrl())
                .set(Tables.PRODUCT.NAME, product.getName())
                .set(Tables.PRODUCT.ACTUAL_PRICE, product.getActualPrice())
                .set(Tables.PRODUCT.PAST_PRICE, product.getPastPrice())
                .set(Tables.PRODUCT.UPDATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(product.getUpdatedAt()), ZoneId.systemDefault()))
                .returning()
                .fetchOne(ProductMapper::mapTo);
    }

    @Override
    public void delete(UserProtoConfiguration.ProductMessage product) {
        dslContext
                .deleteFrom(Tables.PRODUCT)
                .where(Tables.PRODUCT.URL.eq(product.getUrl()))
                .execute();
    }

    @Override
    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage product) {
        return dslContext
                .update(Tables.PRODUCT)
                .set(Tables.PRODUCT.URL, product.getUrl())
                .set(Tables.PRODUCT.NAME, product.getName())
                .set(Tables.PRODUCT.ACTUAL_PRICE, product.getActualPrice())
                .set(Tables.PRODUCT.PAST_PRICE, product.getPastPrice())
                .set(Tables.PRODUCT.UPDATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(product.getUpdatedAt()), ZoneId.systemDefault()))
                .returning()
                .fetchOne(ProductMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.ProductMessage> findByUrl(URI productUrl) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(Tables.PRODUCT.URL.eq(productUrl.toString()))
                .fetchOptional(ProductMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.ProductMessage> findBy(UUID accountUuid, URI productUrl) {
        return dslContext
                .select(Tables.PRODUCT)
                .from(Tables.PRODUCT.join(Tables.ACCOUNT_PRODUCTS).on(Tables.PRODUCT.URL.eq(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL)))
                .where(Tables.PRODUCT.URL.eq(productUrl.toString()).and(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid)))
                .fetchOptional(p -> ProductMapper.mapTo(p.into(Tables.PRODUCT)));
    }

    @Override
    public List<UserProtoConfiguration.ProductMessage> findAllExpired(int timeLimit, DatePart datePart, int count) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(DSL.extract(DSL.currentTimestamp().minus(Tables.PRODUCT.UPDATED_AT), datePart).greaterThan(timeLimit))
                .limit(count)
                .fetch(ProductMapper::mapTo);
    }

    @Override
    public List<UserProtoConfiguration.ProductMessage> findAllBy(UUID uuid, int page, int count) {
        return dslContext
                .select(Tables.PRODUCT)
                .from(Tables.PRODUCT.join(Tables.ACCOUNT_PRODUCTS).on(Tables.PRODUCT.URL.eq(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL)))
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(uuid))
                .limit(count)
                .offset((page - 1) * count)
                .fetch(p -> ProductMapper.mapTo(p.value1()));
    }
}
