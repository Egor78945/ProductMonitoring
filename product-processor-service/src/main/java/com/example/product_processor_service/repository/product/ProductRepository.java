package com.example.product_processor_service.repository.product;

import org.jooq.DatePart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ProductRepository<P> {
    public abstract void save(P product);
    public abstract void saveAll(List<P> products);
    public abstract Optional<P> getByUrl(String url);
    public abstract Optional<P> getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
    public abstract List<P> getAllExpired(int timeLimit, DatePart datePart, int count);
    public abstract List<P> getAllByAccountUuid(UUID uuid, int page, int pageSize);
    public abstract boolean existsByUrl(String url);
    public abstract boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
}
