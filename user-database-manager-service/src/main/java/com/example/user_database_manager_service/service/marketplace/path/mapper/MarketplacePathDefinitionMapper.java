package com.example.user_database_manager_service.service.marketplace.path.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.MarketplacePathDefinitionRecord;

import java.util.List;

public class MarketplacePathDefinitionMapper {
    public static UserProtoConfiguration.MarketplacePathDefinitionMessage mapTo(MarketplacePathDefinitionRecord marketplacePathDefinition) {
        return UserProtoConfiguration.MarketplacePathDefinitionMessage
                .newBuilder()
                .setMarketplaceId(marketplacePathDefinition.getMarketplaceId())
                .setBaseUrl(marketplacePathDefinition.getBaseUrl())
                .build();
    }

    public static UserProtoConfiguration.ListMarketplacePathDefinitionMessage mapTo(List<UserProtoConfiguration.MarketplacePathDefinitionMessage> marketplacePathDefinitionMessageList) {
        return UserProtoConfiguration.ListMarketplacePathDefinitionMessage
                .newBuilder()
                .addAllMarketplacePathDefinitionList(marketplacePathDefinitionMessageList)
                .build();
    }
}
