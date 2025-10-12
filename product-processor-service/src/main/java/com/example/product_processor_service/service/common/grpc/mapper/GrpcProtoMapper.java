package com.example.product_processor_service.service.common.grpc.mapper;

import com.example.grpc.product.ProductServiceProtoConfiguration;

import java.util.List;

public class GrpcProtoMapper {
    public static ProductServiceProtoConfiguration.BooleanMessage mapTo(boolean b) {
        return ProductServiceProtoConfiguration.BooleanMessage.newBuilder()
                .setBoolean(b)
                .build();
    }

    public static ProductServiceProtoConfiguration.StringListMessage mapTo(List<String> list) {
        return ProductServiceProtoConfiguration.StringListMessage.newBuilder()
                .addAllStrings(list)
                .build();
    }
}
