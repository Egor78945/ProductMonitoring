package com.example.product_processor_service.service.product;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public interface ProductService<P> {
    void update(P entity);
    P getByUrl(String url);
    List<P> getAllExpired();
    List<P> getAllByAccountUuid(UUID accountUuid, int page);
    boolean existsByUrl(String url);
    void deleteByAccountUuidAndProductUrl(UUID accountUuid, URI productUrl);
}
