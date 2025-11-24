package com.example.product_processor_service.service.marketplace.path.grpc.client;

import com.example.grpc.user.MarketplacePathDefinitionProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.common.grpc.client.GrpcClientService;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;

import java.util.List;

public abstract class MarketplacePathDefinitionGrpcClientService extends GrpcClientService<MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceBlockingStub> {
    public MarketplacePathDefinitionGrpcClientService(MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceBlockingStub stub) {
        super(stub);
    }

    public UserProtoConfiguration.BooleanMessage isMarketplaceSupported(UserProtoConfiguration.StringMessage marketplaceDomain) {
        return stub.isMarketplaceSupported(marketplaceDomain);
    }

    public List<UserProtoConfiguration.MarketplacePathDefinitionMessage> getSupportedMarketplaces() {
        return stub.getSupportedMarketplaces(UserGrpcMapper.mapTo()).getMarketplacePathDefinitionListList();
    }

    public List<UserProtoConfiguration.MarketplacePathDefinitionMessage> getByMarketplaceDefinitionId(UserProtoConfiguration.LongMessage marketplaceDefinitionId) {
        return stub.getByMarketplaceDefinitionId(marketplaceDefinitionId).getMarketplacePathDefinitionListList();
    }
}
