package com.example.product_processor_service.service.product;

import java.util.List;
import java.util.UUID;

public interface ProductService<P> {
    void save(P product);
    P getByUrl(String url);
    List<P> getAllExpired(int limit);
    boolean existsByUrl(String url);
    List<P> getAllByAccountUuid(UUID accountUuid, int page);
}
