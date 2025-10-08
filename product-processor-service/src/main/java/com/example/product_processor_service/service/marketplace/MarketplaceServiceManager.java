package com.example.product_processor_service.service.marketplace;

import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;
import com.example.product_processor_service.repository.marketplace.definition.path.MarketplacePathDefinitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceServiceManager implements MarketplaceService {
    private final MarketplacePathDefinitionRepository<MarketplacePathDefinition> marketplacePathDefinitionRepository;

    public MarketplaceServiceManager(MarketplacePathDefinitionRepository<MarketplacePathDefinition> marketplacePathDefinitionRepository) {
        this.marketplacePathDefinitionRepository = marketplacePathDefinitionRepository;
    }

    @Override
    public boolean isMarketplaceSupported(String marketplaceDomain) {
        if(marketplaceDomain.isBlank()) {
            throw new IllegalArgumentException("marketplaceDomain cannot be blank");
        }
        return marketplacePathDefinitionRepository.existsByPath(marketplaceDomain);
    }

    @Override
    public List<String> getSupportedMarketplaces() {
        return marketplacePathDefinitionRepository.getAllPaths();
    }
}
