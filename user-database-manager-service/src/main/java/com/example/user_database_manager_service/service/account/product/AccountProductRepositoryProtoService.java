package com.example.user_database_manager_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductRepositoryProtoService implements AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> {
    protected final AccountProductRepository<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductRepository;

    public AccountProductRepositoryProtoService(AccountProductRepository<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductRepository) {
        this.accountProductRepository = accountProductRepository;
    }

    @Override
    public UserProtoConfiguration.AccountUuidProductUriMessage save(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        return accountProductRepository.save(entity);
    }

    @Override
    public UserProtoConfiguration.AccountUuidProductUriMessage update(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
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
    public void deleteAllByUserUuid(UUID userUuid) {
        accountProductRepository.deleteAllByUserUuid(userUuid);
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
