package com.example.product_processor_service.service.marketplace.selector.grpc.client;

import com.example.grpc.user.MarketplaceSelectorProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.common.grpc.client.GrpcClientService;

public abstract class MarketplaceSelectorGrpcClientService extends GrpcClientService<MarketplaceSelectorProtoServiceGrpc.MarketplaceSelectorProtoServiceBlockingStub> {
    public MarketplaceSelectorGrpcClientService(MarketplaceSelectorProtoServiceGrpc.MarketplaceSelectorProtoServiceBlockingStub stub) {
        super(stub);
    }

    public UserProtoConfiguration.MarketplaceSelectorMessage getByMarketplaceId(UserProtoConfiguration.LongMessage marketplaceId) {
        return stub.getByMarketplaceId(marketplaceId);
    }
    public UserProtoConfiguration.MarketplaceSelectorMessage getByMarketplaceName(UserProtoConfiguration.StringMessage marketplaceName) {
        return stub.getByMarketplaceName(marketplaceName);
    }
}
