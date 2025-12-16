package com.example.user_database_manager_service.service.marketplace.path;

import com.example.user_database_manager_service.service.EntityService;

import java.net.URI;
import java.util.List;

public interface MarketplacePathDefinitionService<MPD> extends EntityService<MPD> {
    List<MPD> findAll();
    List<MPD> findAllByMarketplaceDefinitionId(Long id);
}
