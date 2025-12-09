package com.example.product_processor_service.service.marketplace.definition.grpc.client;

import com.example.grpc.user.MarketplaceDefinitionProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.common.grpc.client.GrpcClientService;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;

import java.util.List;

public abstract class MarketplaceDefinitionGrpcClientService extends GrpcClientService<MarketplaceDefinitionProtoServiceGrpc.MarketplaceDefinitionProtoServiceBlockingStub> {
    public MarketplaceDefinitionGrpcClientService(MarketplaceDefinitionProtoServiceGrpc.MarketplaceDefinitionProtoServiceBlockingStub stub) {
        super(stub);
    }

    public List<UserProtoConfiguration.MarketplaceDefinitionMessage> getAll() {
        return stub.getAll(UserGrpcMapper.mapTo()).getMarketplaceDefinitionMessageListList();
    }
}
