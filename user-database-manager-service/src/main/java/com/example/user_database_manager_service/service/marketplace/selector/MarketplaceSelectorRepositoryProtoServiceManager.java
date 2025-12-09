package com.example.user_database_manager_service.service.marketplace.selector;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.repository.marketplace.selector.MarketplaceSelectorRepository;
import com.example.user_database_manager_service.service.marketplace.path.MarketplacePathDefinitionService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceSelectorRepositoryProtoServiceManager extends MarketplaceSelectorRepositoryService<UserProtoConfiguration.MarketplaceSelectorMessage> {
    protected final MarketplacePathDefinitionService<?> marketplacePathDefinitionService;
    public MarketplaceSelectorRepositoryProtoServiceManager(MarketplaceSelectorRepository<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorRepository, MarketplacePathDefinitionService<?> marketplacePathDefinitionService) {
        super(marketplaceSelectorRepository);
        this.marketplacePathDefinitionService = marketplacePathDefinitionService;
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage save(UserProtoConfiguration.MarketplaceSelectorMessage entity) {
        if (marketplacePathDefinitionService.existsById(entity.getId())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("MarketplacePathDefinition is not exists for save MarketplaceSelector: %s", entity));
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage update(UserProtoConfiguration.MarketplaceSelectorMessage entity) {
        if (existsById(entity.getId())) {
            return super.update(entity);
        }
        throw new AlreadyExistsException(String.format("MarketplaceSelector is already exists by id: %s", entity.getId()));
    }
}
