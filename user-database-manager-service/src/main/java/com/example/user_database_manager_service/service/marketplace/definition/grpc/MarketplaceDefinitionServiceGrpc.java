package com.example.user_database_manager_service.service.marketplace.definition.grpc;

import com.example.grpc.user.MarketplaceDefinitionProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.marketplace.definition.MarketplaceDefinitionRepository;
import com.example.user_database_manager_service.service.marketplace.definition.mapper.MarketplaceDefinitionMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MarketplaceDefinitionServiceGrpc extends MarketplaceDefinitionProtoServiceGrpc.MarketplaceDefinitionProtoServiceImplBase {
    private final MarketplaceDefinitionRepository<UserProtoConfiguration.MarketplaceDefinitionMessage> marketplaceDefinitionRepository;

    public MarketplaceDefinitionServiceGrpc(MarketplaceDefinitionRepository<UserProtoConfiguration.MarketplaceDefinitionMessage> marketplaceDefinitionRepository) {
        this.marketplaceDefinitionRepository = marketplaceDefinitionRepository;
    }

    @Override
    public void getAll(UserProtoConfiguration.EmptyMessage request, StreamObserver<UserProtoConfiguration.ListMarketplaceDefinitionMessage> responseObserver) {
        System.out.println("getAll");
        responseObserver.onNext(MarketplaceDefinitionMapper.mapTo(marketplaceDefinitionRepository.findAll()));
        responseObserver.onCompleted();
    }
}
