package com.example.product_processor_service.service.product.grpc;

import com.example.grpc.product.ProductProtoServiceGrpc;
import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.service.account.product.AccountProductService;
import com.example.product_processor_service.service.grpc.mapper.GrpcProtoMapper;
import com.example.product_processor_service.service.product.ProductService;
import com.example.product_processor_service.service.product.mapper.ProductProtoMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class ProductProtoServiceImplBaseManager extends ProductProtoServiceGrpc.ProductProtoServiceImplBase {
    private final ProductService<ProductServiceProtoConfiguration.ProductMessage> productService;
    private final AccountProductService<AccountProduct> accountProductService;

    public ProductProtoServiceImplBaseManager(ProductService<ProductServiceProtoConfiguration.ProductMessage> productService, AccountProductService<AccountProduct> accountProductService) {
        this.productService = productService;
        this.accountProductService = accountProductService;
    }

    @Override
    public void save(ProductServiceProtoConfiguration.AccountProductMessage request, StreamObserver<ProductServiceProtoConfiguration.BooleanMessage> responseObserver) {
        if(productService.existsByUrl(request.getUrl())) {
            productService.save(ProductProtoMapper.mapTo(request));
        }
        accountProductService.save(ProductProtoMapper.mapTo(request.getUrl(), request.getAccountUuid()));
        responseObserver.onNext(GrpcProtoMapper.mapTo(true));
        responseObserver.onCompleted();
    }

    @Override
    public void getByUrl(ProductServiceProtoConfiguration.StringMessage request, StreamObserver<ProductServiceProtoConfiguration.ProductMessage> responseObserver) {
        responseObserver.onNext(productService.getByUrl(request.getString()));
        responseObserver.onCompleted();
    }

    @Override
    public void getAllByAccountUuidAndPage(ProductServiceProtoConfiguration.StringIntMessage request, StreamObserver<ProductServiceProtoConfiguration.ProductListMessage> responseObserver) {
        responseObserver.onNext(ProductProtoMapper.mapTo(productService.getAllByAccountUuid(UUID.fromString(request.getString()), request.getInt())));
        responseObserver.onCompleted();
    }
}
