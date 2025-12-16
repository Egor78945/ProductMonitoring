package com.example.user_database_manager_service.service.account.product.common;

import java.net.URI;
import java.util.UUID;

public interface CommonAccountProductService {
    void deleteAllByAccountUuid(UUID accountUuid);

    void deleteAllByProductUrl(URI productUrl);

    void deleteAllByUserUuid(UUID userUuid);

    void deleteAllByUserEmail(String email);

    void deleteByAccountUuidAndProductUri(UUID accountUuid, URI productUrl);

    boolean existsBy(UUID accountUuid, URI productUrl);

    boolean existsByAccountUuid(UUID accountUuid);

    boolean existsByProductUrl(URI productUrl);
}
