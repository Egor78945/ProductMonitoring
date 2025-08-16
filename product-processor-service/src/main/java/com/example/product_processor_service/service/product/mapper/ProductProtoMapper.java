package com.example.product_processor_service.service.product.mapper;

import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import nu.studer.sample.tables.records.ProductRecord;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

public class ProductProtoMapper {
    public static ProductServiceProtoConfiguration.ProductMessage mapTo(ProductRecord record) {
        return ProductServiceProtoConfiguration.ProductMessage.newBuilder()
                .setUrl(record.getUrl())
                .setActualPrice(record.getActualPrice())
                .setPastPrice(record.getPastPrice())
                .setUpdatedAt(record.getUpdatedAt().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
                .build();
    }

    public static AccountProduct mapTo(String url, String accountUuid) {
        return new AccountProduct(url, UUID.fromString(accountUuid));
    }

    public static ProductServiceProtoConfiguration.ProductMessage mapTo(ProductServiceProtoConfiguration.AccountProductMessage accountProductMessage) {
        return ProductServiceProtoConfiguration.ProductMessage.newBuilder()
                .setUrl(accountProductMessage.getUrl())
                .setActualPrice(accountProductMessage.getActualPrice())
                .setPastPrice(accountProductMessage.getPastPrice())
                .setUpdatedAt(accountProductMessage.getUpdatedAt())
                .build();
    }

    public static ProductServiceProtoConfiguration.ProductListMessage mapTo(List<ProductServiceProtoConfiguration.ProductMessage> productMessageList) {
        return ProductServiceProtoConfiguration.ProductListMessage.newBuilder()
                .addAllProducts(productMessageList)
                .build();
    }
}
