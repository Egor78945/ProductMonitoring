package com.example.user_database_manager_service.service.marketplace.definition;

import com.example.user_database_manager_service.service.EntityService;

import java.util.List;

public interface MarketplaceDefinitionService<MD> extends EntityService<MD> {
    List<MD> findAll();
}
