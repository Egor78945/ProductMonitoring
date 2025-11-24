package com.example.product_processor_service.service.marketplace.definition;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.marketplace.definition.grpc.client.MarketplaceDefinitionGrpcClientService;

import java.util.List;

public abstract class MarketplaceDefinitionProtoService implements MarketplaceDefinitionService<UserProtoConfiguration.MarketplaceDefinitionMessage> {
    protected final MarketplaceDefinitionGrpcClientService marketplaceDefinitionGrpcClientService;

    public MarketplaceDefinitionProtoService(MarketplaceDefinitionGrpcClientService marketplaceDefinitionGrpcClientService) {
        this.marketplaceDefinitionGrpcClientService = marketplaceDefinitionGrpcClientService;
    }

    @Override
    public List<UserProtoConfiguration.MarketplaceDefinitionMessage> getAll() {
        return marketplaceDefinitionGrpcClientService.getAll();
    }
}
