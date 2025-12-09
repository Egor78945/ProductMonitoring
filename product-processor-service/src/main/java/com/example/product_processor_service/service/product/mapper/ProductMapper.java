package com.example.product_processor_service.service.product.mapper;

import com.example.grpc.user.UserProtoConfiguration;

public class ProductMapper {
    public static UserProtoConfiguration.ProductMessage mapTo(String uri, String name, int actualPrice, int pastPrice, long updatedAt) {
        return UserProtoConfiguration.ProductMessage.newBuilder()
                .setUrl(uri)
                .setName(name)
                .setActualPrice(actualPrice)
                .setPastPrice(pastPrice)
                .setUpdatedAt(updatedAt)
                .build();
    }
}
