package com.example.user_database_manager_service.service.product.grpc;

import com.example.grpc.user.ProductProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.product.ProductConsumingRegistrationProtoService;
import com.example.user_database_manager_service.service.product.ProductService;
import com.example.user_database_manager_service.service.product.common.CommonProductService;
import com.example.user_database_manager_service.service.product.mapper.ProductMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jooq.DatePart;
import org.springframework.beans.factory.annotation.Qualifier;

import java.net.URI;
import java.util.UUID;

@GrpcService
public class ProductServiceGrpc extends ProductProtoServiceGrpc.ProductProtoServiceImplBase {
    private final ProductService<UserProtoConfiguration.ProductMessage> productService;
    private final CommonProductService commonProductService;
    private final CommonAccountProductService commonAccountProductService;
    private final ProductConsumingRegistrationProtoService productConsumingRegistrationProtoService;

    public ProductServiceGrpc(ProductService<UserProtoConfiguration.ProductMessage> productService, CommonProductService commonProductService, CommonAccountProductService commonAccountProductService, @Qualifier("productConsumingRegistrationProtoTransactionalServiceManager") ProductConsumingRegistrationProtoService productConsumingRegistrationProtoService) {
        this.productService = productService;
        this.commonProductService = commonProductService;
        this.commonAccountProductService = commonAccountProductService;
        this.productConsumingRegistrationProtoService = productConsumingRegistrationProtoService;
    }

    @Override
    public void save(UserProtoConfiguration.ProductRegistrationMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            productConsumingRegistrationProtoService.register(request);
            responseObserver.onNext(GrpcMapper.mapTo());
            responseObserver.onCompleted();
        } catch (AlreadyExistsException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void update(UserProtoConfiguration.ProductMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            System.out.println(request);
            productService.update(request);
            responseObserver.onNext(GrpcMapper.mapTo());
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteAccountProductByAccountUuidAndProductUri(UserProtoConfiguration.AccountUuidProductUriMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        commonAccountProductService.deleteByAccountUuidAndProductUri(UUID.fromString(request.getAccountUuid()), URI.create(request.getProductUri()));
        responseObserver.onNext(GrpcMapper.mapTo());
        responseObserver.onCompleted();
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
        responseObserver.onNext(GrpcMapper.mapTo(commonProductService.existsByUrl(URI.create(request.getString()))));
        responseObserver.onCompleted();
    }
}
