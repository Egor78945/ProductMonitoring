package com.example.user_database_manager_service.service.marketplace.path;

import com.example.user_database_manager_service.repository.marketplace.path.MarketplacePathDefinitionRepository;

import java.net.URI;
import java.util.List;

public abstract class MarketplacePathDefinitionRepositoryService<MPD> implements MarketplacePathDefinitionService<MPD> {
    protected final MarketplacePathDefinitionRepository<MPD> marketplacePathDefinitionRepository;

    public MarketplacePathDefinitionRepositoryService(MarketplacePathDefinitionRepository<MPD> marketplacePathDefinitionRepository) {
        this.marketplacePathDefinitionRepository = marketplacePathDefinitionRepository;
    }

    @Override
    public MPD save(MPD entity) {
        return marketplacePathDefinitionRepository.save(entity);
    }

    @Override
    public MPD update(MPD entity) {
        return marketplacePathDefinitionRepository.update(entity);
    }

    @Override
    public List<MPD> findAll() {
        return marketplacePathDefinitionRepository.findAll();
    }

    @Override
    public List<MPD> findAllByMarketplaceDefinitionId(Long id) {
        return marketplacePathDefinitionRepository.findAllByMarketplaceDefinitionId(id);
    }

    @Override
    public boolean existsByMarketplaceDomain(String marketplaceDomain) {
        return marketplacePathDefinitionRepository.existsByMarketplaceDomain(marketplaceDomain);
    }

    @Override
    public boolean existsByMarketplaceUrl(URI marketplaceUrl) {
        return marketplacePathDefinitionRepository.existsByMarketplaceUrl(marketplaceUrl.toString());
    }

    @Override
    public boolean existsById(long id) {
        return marketplacePathDefinitionRepository.existsById(id);
    }
}
