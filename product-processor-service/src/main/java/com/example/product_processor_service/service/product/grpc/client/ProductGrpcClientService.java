package com.example.product_processor_service.service.product.grpc.client;

import com.example.grpc.user.ProductProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.service.common.grpc.client.GrpcClientService;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;

public abstract class ProductGrpcClientService extends GrpcClientService<ProductProtoServiceGrpc.ProductProtoServiceBlockingStub> {
    public ProductGrpcClientService(ProductProtoServiceGrpc.ProductProtoServiceBlockingStub stub) {
        super(stub);
    }

    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage productMessage) {
        return stub.save(productMessage);
    }

    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage productMessage) {
        return stub.update(productMessage);
    }

    public UserProtoConfiguration.ProductMessage getByUrl(UserProtoConfiguration.StringMessage stringMessage) {
        return stub.getByUrl(stringMessage);
    }

    public UserProtoConfiguration.ProductListMessage getAllExpired() {
        return stub.getAllExpired(UserGrpcMapper.mapTo());
    }

    public UserProtoConfiguration.ProductListMessage getAllByAccountUuidAndPage(UserProtoConfiguration.StringIntMessage stringIntMessage) {
        return stub.getAllByAccountUuidAndPage(stringIntMessage);
    }

    public UserProtoConfiguration.BooleanMessage existsByUrl(UserProtoConfiguration.StringMessage stringMessage) {
        return stub.existsByUrl(stringMessage);
    }
}
