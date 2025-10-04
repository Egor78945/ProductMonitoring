package com.example.product_processor_service.service.marketplace.grpc;

import com.example.grpc.product.MarketplaceProtoServiceGrpc;
import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.product_processor_service.service.common.grpc.mapper.GrpcProtoMapper;
import com.example.product_processor_service.service.marketplace.MarketplaceService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MarketplaceProtoServiceImplBaseManager extends MarketplaceProtoServiceGrpc.MarketplaceProtoServiceImplBase {
    private final MarketplaceService marketplaceService;

    public MarketplaceProtoServiceImplBaseManager(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    @Override
    public void isMarketplaceSupports(ProductServiceProtoConfiguration.StringMessage request, StreamObserver<ProductServiceProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcProtoMapper.mapTo(marketplaceService.isMarketplaceSupported(request.getString())));
        } catch (Exception ex) {
            responseObserver.onError(ex);
        }
    }
}
