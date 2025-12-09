package com.example.user_database_manager_service.service.marketplace.selector;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.marketplace.selector.MarketplaceSelectorRepository;

public abstract class MarketplaceSelectorRepositoryService<MS> implements MarketplaceSelectorService<MS> {
    protected final MarketplaceSelectorRepository<MS> marketplaceSelectorRepository;

    public MarketplaceSelectorRepositoryService(MarketplaceSelectorRepository<MS> marketplaceSelectorRepository) {
        this.marketplaceSelectorRepository = marketplaceSelectorRepository;
    }

    @Override
    public MS save(MS entity) {
        return marketplaceSelectorRepository.save(entity);
    }

    @Override
    public MS update(MS entity) {
        return marketplaceSelectorRepository.update(entity);
    }

    @Override
    public MS getByMarketplaceId(long id) {
        return marketplaceSelectorRepository.findByMarketplaceId(id).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public MS getByMarketplaceName(String name) {
        return marketplaceSelectorRepository.findByMarketplaceName(name).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsById(long id) {
        return marketplaceSelectorRepository.existsById(id);
    }
}
