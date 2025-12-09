package com.example.user_database_manager_service.service.account.product.mapper;

import com.example.grpc.user.UserProtoConfiguration;

public class AccountProductMapper {
    public static UserProtoConfiguration.AccountUuidProductUriMessage mapTo(String accountUuid, String productUri) {
        return UserProtoConfiguration.AccountUuidProductUriMessage
                .newBuilder()
                .setAccountUuid(accountUuid)
                .setProductUri(productUri)
                .build();
    }
}
