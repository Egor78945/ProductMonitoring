package com.example.user_database_manager_service.service.marketplace.definition.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.MarketplaceDefinitionRecord;

import java.util.List;

public class MarketplaceDefinitionMapper {
    public static UserProtoConfiguration.MarketplaceDefinitionMessage mapTo(MarketplaceDefinitionRecord marketplaceDefinitionRecord) {
        return UserProtoConfiguration.MarketplaceDefinitionMessage.newBuilder()
                .setId(marketplaceDefinitionRecord.getId())
                .setName(marketplaceDefinitionRecord.getName())
                .setProcessorClassName(marketplaceDefinitionRecord.getProcessorClassName())
                .build();
    }

    public static UserProtoConfiguration.ListMarketplaceDefinitionMessage mapTo(List<UserProtoConfiguration.MarketplaceDefinitionMessage> marketplaceDefinitionMessageList) {
        return UserProtoConfiguration.ListMarketplaceDefinitionMessage.newBuilder()
                .addAllMarketplaceDefinitionMessageList(marketplaceDefinitionMessageList)
                .build();
    }
}
