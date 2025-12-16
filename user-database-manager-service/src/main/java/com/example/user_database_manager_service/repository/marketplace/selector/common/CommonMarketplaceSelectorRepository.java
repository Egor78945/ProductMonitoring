package com.example.user_database_manager_service.repository.marketplace.selector.common;

public interface CommonMarketplaceSelectorRepository {
    boolean existsByMarketplaceId(long marketplaceId);
    boolean existsById(long marketplaceId);
}
