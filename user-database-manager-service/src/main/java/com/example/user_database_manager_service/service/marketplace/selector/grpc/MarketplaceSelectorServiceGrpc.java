package com.example.user_database_manager_service.service.marketplace.selector.grpc;

import com.example.grpc.user.MarketplaceSelectorProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.marketplace.selector.MarketplaceSelectorRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MarketplaceSelectorServiceGrpc extends MarketplaceSelectorProtoServiceGrpc.MarketplaceSelectorProtoServiceImplBase {
    private final MarketplaceSelectorRepository<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorRepository;

    public MarketplaceSelectorServiceGrpc(MarketplaceSelectorRepository<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorRepository) {
        this.marketplaceSelectorRepository = marketplaceSelectorRepository;
    }

    @Override
    public void getByMarketplaceId(UserProtoConfiguration.LongMessage request, StreamObserver<UserProtoConfiguration.MarketplaceSelectorMessage> responseObserver) {
        try {
            responseObserver.onNext(marketplaceSelectorRepository.findByMarketplaceId(request.getLong()).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage())));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getByMarketplaceName(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.MarketplaceSelectorMessage> responseObserver) {
        try {
            responseObserver.onNext(marketplaceSelectorRepository.findByMarketplaceName(request.getString()).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage())));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }
}
