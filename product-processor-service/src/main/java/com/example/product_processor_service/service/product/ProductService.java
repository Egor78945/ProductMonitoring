package com.example.product_processor_service.service.product;

import java.util.List;
import java.util.UUID;

public interface ProductService<P> {
    void save(P product);
    void register(P product, UUID accountUuid);
    P getByUrl(String url);
    List<P> getAllExpired(int limit);
    List<P> getAllByAccountUuid(UUID accountUuid, int page);
    boolean existsByUrl(String url);
    boolean existsByUrlAndUserEmail(String url, String userEmail);
}
