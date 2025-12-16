package com.example.user_database_manager_service.service.marketplace.selector;

import com.example.user_database_manager_service.service.EntityService;

public interface MarketplaceSelectorService<MS> extends EntityService<MS> {
    MS getByMarketplaceId(long id);
    MS getByMarketplaceName(String name);
}
