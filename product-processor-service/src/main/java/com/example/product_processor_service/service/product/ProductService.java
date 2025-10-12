package com.example.product_processor_service.service.product;

import com.example.product_processor_service.util.function.Scrypt;
import org.jooq.DatePart;

import java.util.List;
import java.util.UUID;

public interface ProductService<P> {
    P save(P product);
    P update(P product);
    P getByUrl(String url);
    List<P> getAllExpired(int limit);
    List<P> getAllByAccountUuid(UUID accountUuid, int page);
    boolean existsByUrl(String url);
    void transactional(Scrypt scrypt);
}
