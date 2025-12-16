package com.example.user_database_manager_service.service.marketplace.selector.common;

import com.example.user_database_manager_service.repository.marketplace.selector.common.CommonMarketplaceSelectorRepository;

public abstract class CommonMarketplaceSelectorRepositoryService implements CommonMarketplaceSelectorService {
    protected final CommonMarketplaceSelectorRepository commonMarketplaceSelectorRepository;

    public CommonMarketplaceSelectorRepositoryService(CommonMarketplaceSelectorRepository commonMarketplaceSelectorRepository) {
        this.commonMarketplaceSelectorRepository = commonMarketplaceSelectorRepository;
    }

    @Override
    public boolean existsById(long id) {
        return commonMarketplaceSelectorRepository.existsById(id);
    }
}
