package com.example.user_database_manager_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductProtoService implements AccountProductService<UserProtoConfiguration.AccountProductMessage> {
    protected final AccountProductRepository<UserProtoConfiguration.AccountProductMessage> accountProductRepository;

    public AccountProductProtoService(AccountProductRepository<UserProtoConfiguration.AccountProductMessage> accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage save(UserProtoConfiguration.AccountProductMessage entity) {
        return accountProductRepository.save(entity);
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage update(UserProtoConfiguration.AccountProductMessage entity) {
        return accountProductRepository.update(entity);
    }

    @Override
    public void deleteByAccountUuidAndProductUri(UUID accountUuid, URI productUrl) {
        accountProductRepository.deleteByAccountUuidAndProductUri(accountUuid, productUrl);
    }

    @Override
    public void deleteAllByAccountUuid(UUID accountUuid) {
        accountProductRepository.deleteAllByAccountUuid(accountUuid);
    }

    @Override
    public void deleteAllByProductUrl(URI productUrl) {
        accountProductRepository.deleteAllByProductUrl(productUrl);
    }

    @Override
    public boolean existsBy(UUID accountUuid, URI productUrl) {
        return accountProductRepository.existsBy(accountUuid, productUrl);
    }

    @Override
    public boolean existsByAccountUuid(UUID accountUuid) {
        return accountProductRepository.existsByAccountUuid(accountUuid);
    }

    @Override
    public boolean existsByProductUrl(URI productUrl) {
        return accountProductRepository.existsByProductUrl(productUrl);
    }
}
