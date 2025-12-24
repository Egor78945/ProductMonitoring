package com.example.product_processor_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.model.product.ProductRegistrationModel;
import com.example.product_processor_service.service.product.grpc.client.ProductGrpcClientService;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;

public abstract class ProductProtoRegistrationService implements ProductRegistrationService {
    protected final ProductGrpcClientService grpcClientService;

    public ProductProtoRegistrationService(ProductGrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @Override
    public void register(UserProtoConfiguration.ProductRegistrationMessage entity) {
        grpcClientService.save(entity);
    }
}
