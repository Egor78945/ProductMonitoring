package com.example.user_database_manager_service.repository.product;

import com.example.user_database_manager_service.repository.EntityRepository;
import org.jooq.DatePart;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ProductRepository<P> implements EntityRepository<P> {
    public abstract Optional<P> findByUrl(URI url);

    public abstract Optional<P> findBy(UUID accountUuid, URI productUrl);

    public abstract List<P> findAllExpired(int timeLimit, DatePart datePart, int count);

    public abstract List<P> findAllBy(UUID accountUuid, int page, int count);

    public abstract boolean existsByUrl(URI url);

    public abstract boolean existsBy(UUID accountUuid, URI productUrl);
}
