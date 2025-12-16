package com.example.user_database_manager_service.service.marketplace.definition.common;

import com.example.user_database_manager_service.repository.marketplace.definition.common.CommonMarketplaceDefinitionRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonMarketplaceDefinitionRepositoryServiceManager extends CommonMarketplaceDefinitionRepositoryService{
    public CommonMarketplaceDefinitionRepositoryServiceManager(CommonMarketplaceDefinitionRepository commonMarketplaceDefinitionRepository) {
        super(commonMarketplaceDefinitionRepository);
    }
}
