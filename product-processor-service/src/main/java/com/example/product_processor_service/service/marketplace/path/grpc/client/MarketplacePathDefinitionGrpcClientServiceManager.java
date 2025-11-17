package com.example.product_processor_service.service.marketplace.path.grpc.client;

import com.example.grpc.user.MarketplacePathDefinitionProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class MarketplacePathDefinitionGrpcClientServiceManager extends MarketplacePathDefinitionGrpcClientService{
    public MarketplacePathDefinitionGrpcClientServiceManager(MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceBlockingStub stub) {
        super(stub);
    }
}
