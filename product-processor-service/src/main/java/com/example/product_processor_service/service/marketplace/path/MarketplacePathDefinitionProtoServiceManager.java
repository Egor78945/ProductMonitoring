package com.example.product_processor_service.service.marketplace.path;

import com.example.product_processor_service.service.marketplace.path.grpc.client.MarketplacePathDefinitionGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class MarketplacePathDefinitionProtoServiceManager extends MarketplacePathDefinitionProtoService {
    public MarketplacePathDefinitionProtoServiceManager(MarketplacePathDefinitionGrpcClientService marketplacePathDefinitionGrpcClientService) {
        super(marketplacePathDefinitionGrpcClientService);
    }
}
