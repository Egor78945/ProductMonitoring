package com.example.user_database_manager_service.service.marketplace.selector.common;

import com.example.user_database_manager_service.repository.marketplace.selector.common.CommonMarketplaceSelectorRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonMarketplaceSelectorRepositoryServiceManager extends CommonMarketplaceSelectorRepositoryService{
    public CommonMarketplaceSelectorRepositoryServiceManager(CommonMarketplaceSelectorRepository commonMarketplaceSelectorRepository) {
        super(commonMarketplaceSelectorRepository);
    }
}
