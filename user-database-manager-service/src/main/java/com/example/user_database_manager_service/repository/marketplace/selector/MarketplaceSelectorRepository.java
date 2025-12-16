package com.example.user_database_manager_service.repository.marketplace.selector;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;

public abstract class MarketplaceSelectorRepository<MS> implements EntityRepository<MS> {
    public abstract Optional<MS> findByMarketplaceId(long marketplaceId);

    public abstract Optional<MS> findByMarketplaceName(String marketplaceName);
}
