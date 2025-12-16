package com.example.user_database_manager_service.repository.account.product.common;

import java.net.URI;
import java.util.UUID;

public interface CommonAccountProductRepository {
    void deleteByAccountUuidAndProductUri(UUID accountUuid, URI productUrl);

    void deleteAllByAccountUuid(UUID accountUuid);

    void deleteAllByProductUrl(URI productUrl);

    void deleteAllByUserUuid(UUID userUuid);

    void deleteAllByUserEmail(String email);

    boolean existsBy(UUID accountUuid, URI productUrl);

    boolean existsByProductUrl(URI productUrl);

    boolean existsByAccountUuid(UUID accountUuid);
}
