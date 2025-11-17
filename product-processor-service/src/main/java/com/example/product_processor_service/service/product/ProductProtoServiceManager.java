package com.example.product_processor_service.service.product;

import com.example.product_processor_service.service.product.grpc.client.ProductGrpcClientService;
import org.springframework.stereotype.Service;

@Service
public class ProductProtoServiceManager extends ProductProtoService {
    public ProductProtoServiceManager(ProductGrpcClientService productGrpcClientService) {
        super(productGrpcClientService);
    }
}
