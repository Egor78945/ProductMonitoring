package com.example.product_processor_service.service.marketplace.selector;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;
import com.example.product_processor_service.service.marketplace.selector.grpc.client.MarketplaceSelectorGrpcClientService;

public abstract class MarketplaceSelectorProtoService implements MarketplaceSelectorService<UserProtoConfiguration.MarketplaceSelectorMessage> {
    protected final MarketplaceSelectorGrpcClientService marketplaceSelectorGrpcClientService;

    public MarketplaceSelectorProtoService(MarketplaceSelectorGrpcClientService marketplaceSelectorGrpcClientService) {
        this.marketplaceSelectorGrpcClientService = marketplaceSelectorGrpcClientService;
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage getByMarketplaceId(long marketplaceId) {
        return marketplaceSelectorGrpcClientService.getByMarketplaceId(UserGrpcMapper.mapTo(marketplaceId));
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage getByMarketplaceName(String name) {
        return marketplaceSelectorGrpcClientService.getByMarketplaceName(UserGrpcMapper.mapTo(name));
    }
}
