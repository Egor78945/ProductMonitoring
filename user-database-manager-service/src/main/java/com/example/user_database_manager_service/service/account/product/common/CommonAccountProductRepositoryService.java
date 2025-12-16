package com.example.user_database_manager_service.service.account.product.common;

import com.example.user_database_manager_service.repository.account.product.common.CommonAccountProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class CommonAccountProductRepositoryService implements CommonAccountProductService {
    protected final CommonAccountProductRepository commonAccountProductRepository;

    public CommonAccountProductRepositoryService(CommonAccountProductRepository commonAccountProductRepository) {
        this.commonAccountProductRepository = commonAccountProductRepository;
    }

    @Override
    public void deleteAllByAccountUuid(UUID accountUuid) {
        commonAccountProductRepository.deleteAllByAccountUuid(accountUuid);
    }

    @Override
    public void deleteAllByProductUrl(URI productUrl) {
        commonAccountProductRepository.deleteAllByProductUrl(productUrl);
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        commonAccountProductRepository.deleteAllByUserUuid(userUuid);
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        commonAccountProductRepository.deleteAllByUserEmail(email);
    }

    @Override
    public void deleteByAccountUuidAndProductUri(UUID accountUuid, URI productUrl) {
        commonAccountProductRepository.deleteByAccountUuidAndProductUri(accountUuid, productUrl);
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return commonAccountProductRepository.existsBy(accountUuid, productUrl);
    }

    @Override
    public boolean existsByAccountUuid(UUID accountUuid) {
        return commonAccountProductRepository.existsByAccountUuid(accountUuid);
    }

    @Override
    public boolean existsByProductUrl(URI productUrl) {
        return commonAccountProductRepository.existsByProductUrl(productUrl);
    }
}
