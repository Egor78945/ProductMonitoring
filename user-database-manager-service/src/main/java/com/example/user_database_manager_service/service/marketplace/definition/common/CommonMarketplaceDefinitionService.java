package com.example.user_database_manager_service.service.marketplace.definition.common;

public interface CommonMarketplaceDefinitionService {
    boolean existsByName(String name);
    boolean existsById(long id);
}
