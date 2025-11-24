package com.example.product_processor_service.service.marketplace.definition;

import com.example.product_processor_service.service.marketplace.definition.grpc.client.MarketplaceDefinitionGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceDefinitionProtoServiceManager extends MarketplaceDefinitionProtoService {
    public MarketplaceDefinitionProtoServiceManager(MarketplaceDefinitionGrpcClientService marketplaceDefinitionGrpcClientService) {
        super(marketplaceDefinitionGrpcClientService);
    }
}
