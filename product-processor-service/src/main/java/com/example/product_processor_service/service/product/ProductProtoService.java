package com.example.product_processor_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;
import com.example.product_processor_service.service.product.grpc.client.ProductGrpcClientService;

import java.util.List;
import java.util.UUID;

public abstract class ProductProtoService implements ProductService<UserProtoConfiguration.ProductMessage> {
    protected final ProductGrpcClientService productGrpcClientService;

    public ProductProtoService(ProductGrpcClientService productGrpcClientService) {
        this.productGrpcClientService = productGrpcClientService;
    }

    @Override
    public UserProtoConfiguration.ProductMessage save(UserProtoConfiguration.ProductMessage entity) {
        return productGrpcClientService.save(entity);
    }

    @Override
    public UserProtoConfiguration.ProductMessage update(UserProtoConfiguration.ProductMessage entity) {
        return productGrpcClientService.update(entity);
    }

    @Override
    public void delete(UserProtoConfiguration.ProductMessage entity) {
//TODO
    }

    @Override
    public UserProtoConfiguration.ProductMessage getByUrl(String url) {
        return productGrpcClientService.getByUrl(UserGrpcMapper.mapTo(url));
    }

    @Override
    public List<UserProtoConfiguration.ProductMessage> getAllExpired() {
        return productGrpcClientService.getAllExpired().getProductsList();
    }

    @Override
    public List<UserProtoConfiguration.ProductMessage> getAllByAccountUuid(UUID accountUuid, int page) {
        return productGrpcClientService.getAllByAccountUuidAndPage(UserGrpcMapper.mapTo(accountUuid.toString(), page)).getProductsList();
    }

    @Override
    public boolean existsByUrl(String url) {
        return productGrpcClientService.existsByUrl(UserGrpcMapper.mapTo(url)).getBoolean();
    }
}
