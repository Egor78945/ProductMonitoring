package com.example.user_database_manager_service.service.account.product;

import com.example.user_database_manager_service.service.EntityService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public interface AccountProductService<AP> extends EntityService<AP> {
    void deleteAllByAccountUuid(UUID accountUuid);

    void deleteAllByProductUrl(URI productUrl);

    boolean existsBy(UUID accountUuid, URI productUrl);

    boolean existsByAccountUuid(UUID accountUuid);

    boolean existsByProductUrl(URI productUrl);
}
