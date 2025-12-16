package com.example.user_database_manager_service.repository.marketplace.definition.common;

public interface CommonMarketplaceDefinitionRepository {
    boolean existsByName(String name);
    boolean existsById(long id);
}
