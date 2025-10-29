package com.example.product_processor_service.service.marketplace.grpc;

import com.example.grpc.product.MarketplaceProtoServiceGrpc;
import com.example.grpc.product.ProductServiceProtoConfiguration;
import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;
import com.example.product_processor_service.service.common.grpc.mapper.GrpcProtoMapper;
import com.example.product_processor_service.service.marketplace.MarketplacePathService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class MarketplaceProtoServiceImplBaseManager extends MarketplaceProtoServiceGrpc.MarketplaceProtoServiceImplBase {
    private final MarketplacePathService marketplacePathService;

    public MarketplaceProtoServiceImplBaseManager(MarketplacePathService marketplacePathService) {
        this.marketplacePathService = marketplacePathService;
    }

    @Override
    public void isMarketplaceSupports(ProductServiceProtoConfiguration.StringMessage request, StreamObserver<ProductServiceProtoConfiguration.BooleanMessage> responseObserver) {
        try {
            responseObserver.onNext(GrpcProtoMapper.mapTo(marketplacePathService.isMarketplaceSupported(request.getString())));
        } catch (Exception ex) {
            responseObserver.onError(ex);
            return;
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getSupportedMarketplaces(ProductServiceProtoConfiguration.EmptyMessage request, StreamObserver<ProductServiceProtoConfiguration.StringListMessage> responseObserver) {
        List<String> paths = new ArrayList<>();
        for(MarketplacePathDefinition mpd: marketplacePathService.getSupportedMarketplaces()){
            paths.add(mpd.getBaseUrl());
        }
        responseObserver.onNext(GrpcProtoMapper.mapTo(paths));
        responseObserver.onCompleted();
    }
}
