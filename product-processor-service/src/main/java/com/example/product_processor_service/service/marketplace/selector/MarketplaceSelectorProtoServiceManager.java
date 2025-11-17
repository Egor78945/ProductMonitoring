package com.example.product_processor_service.service.marketplace.selector;

import com.example.product_processor_service.service.marketplace.selector.grpc.client.MarketplaceSelectorGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceSelectorProtoServiceManager extends MarketplaceSelectorProtoService {
    public MarketplaceSelectorProtoServiceManager(MarketplaceSelectorGrpcClientService marketplaceSelectorGrpcClientService) {
        super(marketplaceSelectorGrpcClientService);
    }
}
