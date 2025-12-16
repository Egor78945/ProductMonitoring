package com.example.user_database_manager_service.service.product.common;

import java.net.URI;
import java.util.UUID;

public interface CommonProductService {
    boolean existsByUrl(URI url);

    boolean existsBy(UUID accountUuid, URI productUrl);

    void deleteByUrl(URI url);
}
