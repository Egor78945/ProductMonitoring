package com.example.product_processor_service.repository.marketplace.definition.path;

import com.example.product_processor_service.repository.Repository;

import java.util.List;

public abstract class MarketplacePathDefinitionRepository<MPD> extends Repository<MPD> {
    public abstract List<MPD> findByMarketplaceId(long id);

    public abstract List<MPD> findAll();

    public abstract boolean existsByPath(String path);
}
