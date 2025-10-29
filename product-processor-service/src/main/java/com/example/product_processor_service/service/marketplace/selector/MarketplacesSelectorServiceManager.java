package com.example.product_processor_service.service.marketplace.selector;

import com.example.product_processor_service.exception.NotFoundException;
import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;
import com.example.product_processor_service.repository.marketplace.selector.MarketplaceSelectorRepository;
import org.springframework.stereotype.Service;

@Service
public class MarketplacesSelectorServiceManager implements MarketplaceSelectorService<MarketplaceSelector>{
    private final MarketplaceSelectorRepository<MarketplaceSelector> marketplaceSelectorRepository;

    public MarketplacesSelectorServiceManager(MarketplaceSelectorRepository<MarketplaceSelector> marketplaceSelectorRepository) {
        this.marketplaceSelectorRepository = marketplaceSelectorRepository;
    }

    @Override
    public MarketplaceSelector getByMarketplaceId(long marketplaceId) {
        return marketplaceSelectorRepository.findByMarketplaceId(marketplaceId).orElseThrow(() -> new NotFoundException(String.format("marketplace selector not found by marketplace id - %s", marketplaceId)));
    }

    @Override
    public MarketplaceSelector getByMarketplaceName(String name) {
        return marketplaceSelectorRepository.findByMarketplaceName(name).orElseThrow(() -> new NotFoundException(String.format("marketplace selector not found by name - %s", name)));
    }
}
