package com.example.user_database_manager_service.service.marketplace.path.grpc;

import com.example.grpc.user.MarketplacePathDefinitionProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.marketplace.path.MarketplacePathDefinitionRepository;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.marketplace.path.mapper.MarketplacePathDefinitionMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MarketplacePathDefinitionServiceGrpc extends MarketplacePathDefinitionProtoServiceGrpc.MarketplacePathDefinitionProtoServiceImplBase {
    private final MarketplacePathDefinitionRepository<UserProtoConfiguration.MarketplacePathDefinitionMessage> marketplacePathDefinitionRepository;

    public MarketplacePathDefinitionServiceGrpc(MarketplacePathDefinitionRepository<UserProtoConfiguration.MarketplacePathDefinitionMessage> marketplacePathDefinitionRepository) {
        this.marketplacePathDefinitionRepository = marketplacePathDefinitionRepository;
    }

    @Override
    public void isMarketplaceSupported(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        responseObserver.onNext(GrpcMapper.mapTo(marketplacePathDefinitionRepository.existsByMarketplaceDomain(request.getString())));
        responseObserver.onCompleted();
    }

    @Override
    public void getSupportedMarketplaces(UserProtoConfiguration.EmptyMessage request, StreamObserver<UserProtoConfiguration.ListMarketplacePathDefinitionMessage> responseObserver) {
        responseObserver.onNext(MarketplacePathDefinitionMapper.mapTo(marketplacePathDefinitionRepository.findAllSupported()));
        responseObserver.onCompleted();
    }

    @Override
    public void getByMarketplaceDefinitionId(UserProtoConfiguration.LongMessage request, StreamObserver<UserProtoConfiguration.ListMarketplacePathDefinitionMessage> responseObserver) {
        responseObserver.onNext(MarketplacePathDefinitionMapper.mapTo(marketplacePathDefinitionRepository.findAllByMarketplaceDefinitionId(request.getLong())));
        responseObserver.onCompleted();
    }
}
