package com.example.product_processor_service.repository.product;

import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.product_processor_service.service.product.mapper.ProductProtoMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProtoProductRepositoryManager extends ProtoProductRepository<ProductServiceProtoConfiguration.ProductMessage>{
    public ProtoProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public void save(ProductServiceProtoConfiguration.ProductMessage product) {
        dslContext
                .insertInto(Tables.PRODUCT)
                .set(Tables.PRODUCT.URL, product.getUrl())
                .set(Tables.PRODUCT.NAME, product.getName())
                .set(Tables.PRODUCT.ACTUAL_PRICE, product.getActualPrice())
                .set(Tables.PRODUCT.PAST_PRICE, product.getPastPrice())
                .set(Tables.PRODUCT.UPDATED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(product.getUpdatedAt()), ZoneId.systemDefault()))
                .execute();
    }

    @Override
    public void saveAll(List<ProductServiceProtoConfiguration.ProductMessage> products) {
        for (ProductServiceProtoConfiguration.ProductMessage product : products) {
            save(product);
        }
    }

    @Override
    public Optional<ProductServiceProtoConfiguration.ProductMessage> getByUrl(String productUrl) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(Tables.PRODUCT.URL.eq(productUrl))
                .fetchOptional(ProductProtoMapper::mapTo);
    }

    @Override
    public Optional<ProductServiceProtoConfiguration.ProductMessage> getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl) {
        return dslContext
                .select(Tables.PRODUCT)
                .from(Tables.PRODUCT.join(Tables.ACCOUNT_PRODUCTS).on(Tables.PRODUCT.URL.eq(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL)))
                .where(Tables.PRODUCT.URL.eq(productUrl).and(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(accountUuid)))
                .fetchOptional(p -> ProductProtoMapper.mapTo(p.into(Tables.PRODUCT)));
    }

    @Override
    public List<ProductServiceProtoConfiguration.ProductMessage> getAllExpired(int timeLimit, DatePart datePart, int count) {
        return dslContext
                .selectFrom(Tables.PRODUCT)
                .where(DSL.extract(DSL.currentTimestamp().minus(Tables.PRODUCT.UPDATED_AT), datePart).greaterThan(timeLimit))
                .limit(count)
                .fetchStreamInto(Tables.PRODUCT)
                .map(ProductProtoMapper::mapTo)
                .toList();
    }

    @Override
    public List<ProductServiceProtoConfiguration.ProductMessage> getAllByAccountUuid(UUID uuid, int page, int pageSize) {
        return dslContext
                .selectFrom(Tables.PRODUCT.join(Tables.ACCOUNT_PRODUCTS).on(Tables.PRODUCT.URL.eq(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL)))
                .where(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID.eq(uuid))
                .limit(pageSize)
                .offset((page - 1) * pageSize)
                .fetchStreamInto(Tables.PRODUCT)
                .map(ProductProtoMapper::mapTo)
                .toList();
    }
}
