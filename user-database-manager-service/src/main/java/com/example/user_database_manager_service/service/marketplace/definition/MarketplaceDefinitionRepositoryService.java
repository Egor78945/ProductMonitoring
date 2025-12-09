package com.example.user_database_manager_service.service.marketplace.definition;

import com.example.user_database_manager_service.repository.marketplace.definition.MarketplaceDefinitionRepository;

import java.util.List;

public abstract class MarketplaceDefinitionRepositoryService<MD> implements MarketplaceDefinitionService<MD> {
    protected final MarketplaceDefinitionRepository<MD> marketplaceDefinitionRepository;

    public MarketplaceDefinitionRepositoryService(MarketplaceDefinitionRepository<MD> marketplaceDefinitionRepository) {
        this.marketplaceDefinitionRepository = marketplaceDefinitionRepository;
    }

    @Override
    public MD save(MD entity) {
        return marketplaceDefinitionRepository.save(entity);
    }

    @Override
    public MD update(MD entity) {
        return marketplaceDefinitionRepository.update(entity);
    }

    @Override
    public List<MD> findAll() {
        return marketplaceDefinitionRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return marketplaceDefinitionRepository.existsByName(name);
    }

    @Override
    public boolean existsById(long id) {
        return marketplaceDefinitionRepository.existsById(id);
    }
}
