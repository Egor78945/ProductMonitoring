package com.example.product_processor_service.util.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.model.product.ProductRegistrationModel;
import com.example.product_processor_service.model.product.entity.Product;

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

    public static UserProtoConfiguration.ProductMessage mapToProductMessage(Product product) {
        return UserProtoConfiguration.ProductMessage.newBuilder()
                .setUrl(product.getUrl())
                .setName(product.getName())
                .setActualPrice(product.getActualPrice())
                .setPastPrice(product.getPastPrice())
                .setUpdatedAt(product.getUpdatedAt())
                .build();
    }

    public static UserProtoConfiguration.ProductMessage mapToProductMessage(String uri) {
        return UserProtoConfiguration.ProductMessage.newBuilder()
                .setUrl(uri)
                .build();
    }

    public static UserProtoConfiguration.ProductRegistrationMessage mapToProductRegistrationMessage(ProductRegistrationModel productRegistrationModel) {
        return UserProtoConfiguration.ProductRegistrationMessage.newBuilder()
                .setProduct(mapToProductMessage(productRegistrationModel))
                .setAccountUuid(productRegistrationModel.getAccountUuid())
                .build();
    }

    public static UserProtoConfiguration.ProductRegistrationMessage mapToProductRegistrationMessage(String uri, String accountUuid) {
        return UserProtoConfiguration.ProductRegistrationMessage.newBuilder()
                .setProduct(mapToProductMessage(uri))
                .setAccountUuid(accountUuid)
                .build();
    }

    public static UserProtoConfiguration.AccountUuidProductUriMessage mapTo(String accountUuid, String productUri) {
        return UserProtoConfiguration.AccountUuidProductUriMessage.newBuilder()
                .setAccountUuid(accountUuid)
                .setProductUri(productUri)
                .build();
    }
}
