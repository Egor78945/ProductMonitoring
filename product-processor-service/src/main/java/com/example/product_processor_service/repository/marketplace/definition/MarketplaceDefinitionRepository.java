package com.example.product_processor_service.repository.marketplace.definition;

import com.example.product_processor_service.repository.Repository;

import java.util.List;

public abstract class MarketplaceDefinitionRepository<MD> extends Repository<MD> {
    public abstract List<MD> findAll();
}
