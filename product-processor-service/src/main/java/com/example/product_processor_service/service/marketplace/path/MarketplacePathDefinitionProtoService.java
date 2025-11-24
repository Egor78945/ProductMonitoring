package com.example.product_processor_service.service.marketplace.path;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;
import com.example.product_processor_service.service.marketplace.path.grpc.client.MarketplacePathDefinitionGrpcClientService;

import java.util.List;

public abstract class MarketplacePathDefinitionProtoService implements MarketplacePathDefinitionService<UserProtoConfiguration.MarketplacePathDefinitionMessage> {
    protected final MarketplacePathDefinitionGrpcClientService marketplacePathDefinitionGrpcClientService;

    public MarketplacePathDefinitionProtoService(MarketplacePathDefinitionGrpcClientService marketplacePathDefinitionGrpcClientService) {
        this.marketplacePathDefinitionGrpcClientService = marketplacePathDefinitionGrpcClientService;
    }

    @Override
    public boolean isMarketplaceSupported(String marketplaceDomain) {
        return marketplacePathDefinitionGrpcClientService.isMarketplaceSupported(UserGrpcMapper.mapTo(marketplaceDomain)).getBoolean();
    }

    @Override
    public List<UserProtoConfiguration.MarketplacePathDefinitionMessage> getSupportedMarketplaces() {
        return marketplacePathDefinitionGrpcClientService.getSupportedMarketplaces();
    }

    @Override
    public List<UserProtoConfiguration.MarketplacePathDefinitionMessage> getByMarketplaceDefinitionId(long marketplaceDefinitionId) {
        return marketplacePathDefinitionGrpcClientService.getByMarketplaceDefinitionId(UserGrpcMapper.mapTo(marketplaceDefinitionId));
    }
}
