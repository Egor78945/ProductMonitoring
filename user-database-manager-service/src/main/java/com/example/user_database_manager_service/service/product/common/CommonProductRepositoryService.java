package com.example.user_database_manager_service.service.product.common;

import com.example.user_database_manager_service.repository.product.common.CommonProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class CommonProductRepositoryService implements CommonProductService {
    protected final CommonProductRepository commonProductRepository;

    public CommonProductRepositoryService(CommonProductRepository commonProductRepository) {
        this.commonProductRepository = commonProductRepository;
    }

    @Override
    public void deleteByUrl(URI url) {
        commonProductRepository.deleteByUrl(url);
    }

    @Override
    public boolean existsByUrl(URI url) {
        return commonProductRepository.existsByUrl(url);
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return commonProductRepository.existsBy(accountUuid, productUrl);
    }
}
