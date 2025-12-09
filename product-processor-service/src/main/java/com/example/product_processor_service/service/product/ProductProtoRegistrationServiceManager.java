package com.example.product_processor_service.service.product;

import com.example.product_processor_service.service.product.grpc.client.ProductGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class ProductProtoRegistrationServiceManager extends ProductProtoRegistrationService{
    public ProductProtoRegistrationServiceManager(ProductGrpcClientService grpcClientService) {
        super(grpcClientService);
    }
}
