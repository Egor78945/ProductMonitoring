package com.example.user_database_manager_service.service.marketplace.selector.mapper;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.MarketplaceSelectorRecord;

public class MarketplaceSelectorMapper {
    public static UserProtoConfiguration.MarketplaceSelectorMessage mapTo(MarketplaceSelectorRecord marketplaceSelectorRecord) {
        return UserProtoConfiguration.MarketplaceSelectorMessage
                .newBuilder()
                .setId(marketplaceSelectorRecord.getId())
                .setMarketplaceId(marketplaceSelectorRecord.getMarketplaceId())
                .setProductName(marketplaceSelectorRecord.getProductName())
                .setProductPrice(marketplaceSelectorRecord.getProductPrice())
                .build();
    }
}
