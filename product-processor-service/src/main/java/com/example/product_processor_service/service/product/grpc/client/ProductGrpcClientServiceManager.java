package com.example.product_processor_service.service.product.grpc.client;

import com.example.grpc.user.ProductProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class ProductGrpcClientServiceManager extends ProductGrpcClientService {
    public ProductGrpcClientServiceManager(ProductProtoServiceGrpc.ProductProtoServiceBlockingStub stub) {
        super(stub);
    }
}
