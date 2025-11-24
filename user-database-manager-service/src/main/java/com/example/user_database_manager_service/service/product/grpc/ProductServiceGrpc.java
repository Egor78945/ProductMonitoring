package com.example.user_database_manager_service.service.product.grpc;

import com.example.grpc.user.ProductProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.product.ProductService;
import com.example.user_database_manager_service.service.product.mapper.ProductMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jooq.DatePart;

import java.net.URI;
import java.util.UUID;

@GrpcService
public class ProductServiceGrpc extends ProductProtoServiceGrpc.ProductProtoServiceImplBase {
    private final ProductService<UserProtoConfiguration.ProductMessage> productService;

    public ProductServiceGrpc(ProductService<UserProtoConfiguration.ProductMessage> productService) {
        this.productService = productService;
    }

    @Override
    public void save(UserProtoConfiguration.ProductMessage request, StreamObserver<UserProtoConfiguration.ProductMessage> responseObserver) {
        try {
            responseObserver.onNext(productService.save(request));
            responseObserver.onCompleted();
        } catch (AlreadyExistsException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void update(UserProtoConfiguration.ProductMessage request, StreamObserver<UserProtoConfiguration.ProductMessage> responseObserver) {
        try {
            System.out.println(request);
            responseObserver.onNext(productService.update(request));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getByUrl(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.ProductMessage> responseObserver) {
        try {
            responseObserver.onNext(productService.findByUrl(URI.create(request.getString())).orElseThrow(() ->
                    new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage())));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getAllExpired(UserProtoConfiguration.EmptyMessage request, StreamObserver<UserProtoConfiguration.ProductListMessage> responseObserver) {
        responseObserver.onNext(ProductMapper.mapTo(productService.findAllExpired(1, DatePart.WEEK, 1000)));
        responseObserver.onCompleted();
    }

    @Override
    public void getAllByAccountUuidAndPage(UserProtoConfiguration.StringIntMessage request, StreamObserver<UserProtoConfiguration.ProductListMessage> responseObserver) {
        responseObserver.onNext(ProductMapper.mapTo(productService.findAllBy(UUID.fromString(request.getString()), request.getInt(), 10)));
        responseObserver.onCompleted();
    }

    @Override
    public void existsByUrl(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        responseObserver.onNext(GrpcMapper.mapTo(productService.existsByUrl(URI.create(request.getString()))));
        responseObserver.onCompleted();
    }
}
