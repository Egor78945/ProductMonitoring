package com.example.user_database_manager_service.repository.account.product;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public abstract class AccountProductRepository<AP> implements EntityRepository<AP> {
    public abstract void deleteAllByAccountUuid(UUID accountUuid);
    public abstract void deleteAllByProductUrl(URI productUrl);
    public abstract boolean existsBy(UUID accountUuid, URI productUrl);
    public abstract boolean existsByProductUrl(URI productUrl);
    public abstract boolean existsByAccountUuid(UUID accountUuid);
}
