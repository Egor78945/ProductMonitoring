package com.example.user_database_manager_service.service.marketplace.path.common;

import java.net.URI;

public interface CommonMarketplacePathDefinitionService {
    boolean existsByMarketplaceDomain(String marketplaceDomain);
    boolean existsByMarketplaceUrl(URI marketplaceUrl);
    boolean existsById(long id);
}
