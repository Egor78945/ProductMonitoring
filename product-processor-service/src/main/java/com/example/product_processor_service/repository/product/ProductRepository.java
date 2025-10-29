package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.repository.Repository;
import org.jooq.DatePart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ProductRepository<P> extends Repository<P> {
    public abstract Optional<P> findByUrl(String url);

    public abstract Optional<P> findByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);

    public abstract List<P> findAllExpired(int timeLimit, DatePart datePart, int count);

    public abstract List<P> findAllByAccountUuid(UUID uuid, int page, int pageSize);

    public abstract boolean existsByUrl(String url);

    public abstract boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
}
