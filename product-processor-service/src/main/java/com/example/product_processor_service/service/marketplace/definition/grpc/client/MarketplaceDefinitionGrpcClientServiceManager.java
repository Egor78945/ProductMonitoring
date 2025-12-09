package com.example.product_processor_service.service.marketplace.definition.grpc.client;

import com.example.grpc.user.MarketplaceDefinitionProtoServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceDefinitionGrpcClientServiceManager extends MarketplaceDefinitionGrpcClientService{
    public MarketplaceDefinitionGrpcClientServiceManager(MarketplaceDefinitionProtoServiceGrpc.MarketplaceDefinitionProtoServiceBlockingStub stub) {
        super(stub);
    }
}
