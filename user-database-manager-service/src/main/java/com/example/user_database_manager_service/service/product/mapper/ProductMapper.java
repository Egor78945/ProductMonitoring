package com.example.user_database_manager_service.service.product.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.ProductRecord;

import java.time.ZoneOffset;
import java.util.List;

public class ProductMapper {
    public static UserProtoConfiguration.ProductMessage mapTo(ProductRecord record) {
        return UserProtoConfiguration.ProductMessage.newBuilder()
                .setUrl(record.getUrl())
                .setActualPrice(record.getActualPrice())
                .setPastPrice(record.getPastPrice())
                .setUpdatedAt(record.getUpdatedAt().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
                .build();
    }

    public static UserProtoConfiguration.ProductListMessage mapTo(List<UserProtoConfiguration.ProductMessage> productMessageList) {
        return UserProtoConfiguration.ProductListMessage.newBuilder()
                .addAllProducts(productMessageList)
                .build();
    }
}
