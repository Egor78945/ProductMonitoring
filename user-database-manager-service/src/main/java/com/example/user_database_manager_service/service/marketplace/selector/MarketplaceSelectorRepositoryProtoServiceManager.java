package com.example.user_database_manager_service.service.marketplace.selector;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.repository.marketplace.selector.MarketplaceSelectorRepository;
import com.example.user_database_manager_service.service.marketplace.path.common.CommonMarketplacePathDefinitionService;
import com.example.user_database_manager_service.service.marketplace.selector.common.CommonMarketplaceSelectorService;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceSelectorRepositoryProtoServiceManager extends MarketplaceSelectorRepositoryService<UserProtoConfiguration.MarketplaceSelectorMessage> {
    protected final CommonMarketplacePathDefinitionService commonMarketplacePathDefinitionService;
    protected final CommonMarketplaceSelectorService commonMarketplaceSelectorService;
    public MarketplaceSelectorRepositoryProtoServiceManager(MarketplaceSelectorRepository<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorRepository, CommonMarketplacePathDefinitionService commonMarketplacePathDefinitionService, CommonMarketplaceSelectorService commonMarketplaceSelectorService) {
        super(marketplaceSelectorRepository);
        this.commonMarketplacePathDefinitionService = commonMarketplacePathDefinitionService;
        this.commonMarketplaceSelectorService = commonMarketplaceSelectorService;
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage save(UserProtoConfiguration.MarketplaceSelectorMessage entity) {
        if (commonMarketplacePathDefinitionService.existsById(entity.getId())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("MarketplacePathDefinition is not exists for save MarketplaceSelector: %s", entity));
    }

    @Override
    public UserProtoConfiguration.MarketplaceSelectorMessage update(UserProtoConfiguration.MarketplaceSelectorMessage entity) {
        if (commonMarketplaceSelectorService.existsById(entity.getId())) {
            return super.update(entity);
        }
        throw new AlreadyExistsException(String.format("MarketplaceSelector is already exists by id: %s", entity.getId()));
    }
}
