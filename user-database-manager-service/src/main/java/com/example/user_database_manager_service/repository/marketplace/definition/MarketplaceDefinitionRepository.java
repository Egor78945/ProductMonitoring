package com.example.user_database_manager_service.repository.marketplace.definition;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.List;

public abstract class MarketplaceDefinitionRepository<MD> implements EntityRepository<MD> {
    public abstract List<MD> findAll();
    public abstract boolean existsByName(String name);
    public abstract boolean existsById(long id);
}
