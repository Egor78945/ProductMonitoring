package com.example.product_processor_service.util.mapper;

import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.grpc.user.UserProtoConfiguration;

import java.util.List;

public class UserGrpcMapper {
    public static UserProtoConfiguration.StringIntMessage mapTo(String string, int intt) {
        return UserProtoConfiguration.StringIntMessage.newBuilder()
                .setString(string)
                .setInt(intt)
                .build();
    }

    public static UserProtoConfiguration.StringMessage mapTo(String string) {
        return UserProtoConfiguration.StringMessage.newBuilder()
                .setString(string)
                .build();
    }

    public static UserProtoConfiguration.BooleanMessage mapTo(boolean b) {
        return UserProtoConfiguration.BooleanMessage.newBuilder()
                .setBoolean(b)
                .build();
    }

    public static UserProtoConfiguration.LongMessage mapTo(long longg) {
        return UserProtoConfiguration.LongMessage.newBuilder()
                .setLong(longg)
                .build();
    }

    public static UserProtoConfiguration.EmptyMessage mapTo() {
        return UserProtoConfiguration.EmptyMessage.newBuilder()
                .build();
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

    public static UserProtoConfiguration.AccountProductMessage mapTo(String uri, String accountUuid) {
        return UserProtoConfiguration.AccountProductMessage.newBuilder()
                .setProductUrl(uri)
                .setAccountUuid(accountUuid)
                .build();
    }
}
