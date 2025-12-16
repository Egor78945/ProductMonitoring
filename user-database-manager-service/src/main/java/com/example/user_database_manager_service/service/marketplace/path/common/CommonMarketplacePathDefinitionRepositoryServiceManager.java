package com.example.user_database_manager_service.service.marketplace.path.common;

import com.example.user_database_manager_service.repository.marketplace.path.common.CommonMarketplacePathDefinitionRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonMarketplacePathDefinitionRepositoryServiceManager extends CommonMarketplacePathDefinitionRepositoryService{
    public CommonMarketplacePathDefinitionRepositoryServiceManager(CommonMarketplacePathDefinitionRepository commonMarketplacePathDefinitionRepository) {
        super(commonMarketplacePathDefinitionRepository);
    }
}
