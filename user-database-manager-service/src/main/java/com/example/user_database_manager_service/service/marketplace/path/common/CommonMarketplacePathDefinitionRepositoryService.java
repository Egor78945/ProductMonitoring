package com.example.user_database_manager_service.service.marketplace.path.common;

import com.example.user_database_manager_service.repository.marketplace.path.common.CommonMarketplacePathDefinitionRepository;

import java.net.URI;

public abstract class CommonMarketplacePathDefinitionRepositoryService implements CommonMarketplacePathDefinitionService {
    protected final CommonMarketplacePathDefinitionRepository commonMarketplacePathDefinitionRepository;

    public CommonMarketplacePathDefinitionRepositoryService(CommonMarketplacePathDefinitionRepository commonMarketplacePathDefinitionRepository) {
        this.commonMarketplacePathDefinitionRepository = commonMarketplacePathDefinitionRepository;
    }

    @Override
    public boolean existsByMarketplaceDomain(String marketplaceDomain) {
        return commonMarketplacePathDefinitionRepository.existsByMarketplaceDomain(marketplaceDomain);
    }

    @Override
    public boolean existsByMarketplaceUrl(URI marketplaceUrl) {
        return commonMarketplacePathDefinitionRepository.existsByMarketplaceUrl(marketplaceUrl.toString());
    }

    @Override
    public boolean existsById(long id) {
        return commonMarketplacePathDefinitionRepository.existsById(id);
    }
}
