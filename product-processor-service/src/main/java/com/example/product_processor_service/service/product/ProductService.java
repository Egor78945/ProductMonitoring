package com.example.product_processor_service.service.product;

import com.example.product_processor_service.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface ProductService<P> extends EntityService<P> {
    P getByUrl(String url);
    List<P> getAllExpired();
    List<P> getAllByAccountUuid(UUID accountUuid, int page);
    boolean existsByUrl(String url);
}
