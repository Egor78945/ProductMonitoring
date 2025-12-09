package com.example.user_database_manager_service.service.marketplace.selector.grpc;

import com.example.grpc.user.MarketplaceSelectorProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.service.marketplace.selector.MarketplaceSelectorService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MarketplaceSelectorServiceGrpc extends MarketplaceSelectorProtoServiceGrpc.MarketplaceSelectorProtoServiceImplBase {
    private final MarketplaceSelectorService<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorService;

    public MarketplaceSelectorServiceGrpc(MarketplaceSelectorService<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorService) {
        this.marketplaceSelectorService = marketplaceSelectorService;
    }

    @Override
    public void getByMarketplaceId(UserProtoConfiguration.LongMessage request, StreamObserver<UserProtoConfiguration.MarketplaceSelectorMessage> responseObserver) {
        try {
            responseObserver.onNext(marketplaceSelectorService.getByMarketplaceId(request.getLong()));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getByMarketplaceName(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.MarketplaceSelectorMessage> responseObserver) {
        try {
            responseObserver.onNext(marketplaceSelectorService.getByMarketplaceName(request.getString()));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }
}
