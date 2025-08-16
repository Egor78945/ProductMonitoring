package com.example.product_processor_service.service.grpc.mapper;

import com.example.grpc.product.ProductServiceProtoConfiguration;

public class GrpcProtoMapper {
    public static ProductServiceProtoConfiguration.BooleanMessage mapTo(boolean b) {
        return ProductServiceProtoConfiguration.BooleanMessage.newBuilder()
                .setBoolean(b)
                .build();
    }
}
