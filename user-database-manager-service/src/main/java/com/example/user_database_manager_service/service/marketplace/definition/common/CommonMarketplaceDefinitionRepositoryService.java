package com.example.user_database_manager_service.service.marketplace.definition.common;

import com.example.user_database_manager_service.repository.marketplace.definition.common.CommonMarketplaceDefinitionRepository;

public abstract class CommonMarketplaceDefinitionRepositoryService implements CommonMarketplaceDefinitionService {
    protected final CommonMarketplaceDefinitionRepository commonMarketplaceDefinitionRepository;

    public CommonMarketplaceDefinitionRepositoryService(CommonMarketplaceDefinitionRepository commonMarketplaceDefinitionRepository) {
        this.commonMarketplaceDefinitionRepository = commonMarketplaceDefinitionRepository;
    }

    @Override
    public boolean existsByName(String name) {
        return commonMarketplaceDefinitionRepository.existsByName(name);
    }

    @Override
    public boolean existsById(long id) {
        return commonMarketplaceDefinitionRepository.existsById(id);
    }
}
