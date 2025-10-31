package com.example.user_database_manager_service.service.account.product.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.AccountProductsRecord;

public class AccountProductMapper {
    public static UserProtoConfiguration.AccountProductMessage mapTo(AccountProductsRecord accountProductsRecord) {
        return UserProtoConfiguration.AccountProductMessage
                .newBuilder()
                .setAccountUuid(accountProductsRecord.getAccountUuid().toString())
                .setProductUrl(accountProductsRecord.getProductUrl())
                .build();
    }
}
