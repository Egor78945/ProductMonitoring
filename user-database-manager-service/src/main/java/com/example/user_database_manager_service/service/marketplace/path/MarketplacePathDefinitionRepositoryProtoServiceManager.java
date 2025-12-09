package com.example.user_database_manager_service.service.marketplace.path;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.marketplace.path.MarketplacePathDefinitionRepository;
import com.example.user_database_manager_service.service.marketplace.definition.MarketplaceDefinitionService;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class MarketplacePathDefinitionRepositoryProtoServiceManager extends MarketplacePathDefinitionRepositoryService<UserProtoConfiguration.MarketplacePathDefinitionMessage> {
    protected final MarketplaceDefinitionService<?> marketplaceDefinitionService;

    public MarketplacePathDefinitionRepositoryProtoServiceManager(MarketplacePathDefinitionRepository<UserProtoConfiguration.MarketplacePathDefinitionMessage> marketplacePathDefinitionRepository, MarketplaceDefinitionService<?> marketplaceDefinitionService) {
        super(marketplacePathDefinitionRepository);
        this.marketplaceDefinitionService = marketplaceDefinitionService;
    }

    @Override
    public UserProtoConfiguration.MarketplacePathDefinitionMessage save(UserProtoConfiguration.MarketplacePathDefinitionMessage entity) {
        if (!existsByMarketplaceUrl(URI.create(entity.getBaseUrl()))) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("MarketplacePathDefinition is already exists by url: %s", entity.getBaseUrl()));
    }

    @Override
    public UserProtoConfiguration.MarketplacePathDefinitionMessage update(UserProtoConfiguration.MarketplacePathDefinitionMessage entity) {
        if (existsByMarketplaceUrl(URI.create(entity.getBaseUrl()))) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("MarketplacePathDefinition is not found by url: %s", entity.getBaseUrl()));
    }
}
