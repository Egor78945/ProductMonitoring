package com.example.product_processor_service.service.marketplace.selector.grpc.client;

import com.example.grpc.user.MarketplaceSelectorProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceSelectorGrpcClientServiceManager extends MarketplaceSelectorGrpcClientService{
    public MarketplaceSelectorGrpcClientServiceManager(MarketplaceSelectorProtoServiceGrpc.MarketplaceSelectorProtoServiceBlockingStub stub) {
        super(stub);
    }
}
