package com.example.user_database_manager_service.repository.marketplace.path.common;

public interface CommonMarketplacePathDefinitionRepository {
    boolean existsByMarketplaceDomain(String marketplaceDomain);
    boolean existsByMarketplaceUrl(String marketplaceUrl);
    boolean existsById(long id);
}
