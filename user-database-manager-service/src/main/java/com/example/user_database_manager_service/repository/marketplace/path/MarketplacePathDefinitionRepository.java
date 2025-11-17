package com.example.user_database_manager_service.repository.marketplace.path;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.List;

public abstract class MarketplacePathDefinitionRepository<MPD> implements EntityRepository<MPD> {
    public abstract boolean existsByMarketplaceDomain(String marketplaceDomain);
    public abstract List<MPD> findAllSupported();
    public abstract List<MPD> findAllByMarketplaceDefinitionId(Long id);
}
