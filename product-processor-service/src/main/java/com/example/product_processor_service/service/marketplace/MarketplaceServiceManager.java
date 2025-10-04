package com.example.product_processor_service.service.marketplace;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import com.example.product_processor_service.repository.marketplace.definition.MarketplaceDefinitionRepository;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceServiceManager implements MarketplaceService {
    private final MarketplaceDefinitionRepository<MarketplaceDefinition> marketplaceDefinitionRepository;

    public MarketplaceServiceManager(MarketplaceDefinitionRepository<MarketplaceDefinition> marketplaceDefinitionRepository) {
        this.marketplaceDefinitionRepository = marketplaceDefinitionRepository;
    }

    @Override
    public boolean isMarketplaceSupported(String marketplaceDomain) {
        if(marketplaceDomain.isBlank()) {
            throw new IllegalArgumentException("marketplaceDomain cannot be blank");
        }
        return marketplaceDefinitionRepository.isMarketplaceSupported(marketplaceDomain);
    }
}
