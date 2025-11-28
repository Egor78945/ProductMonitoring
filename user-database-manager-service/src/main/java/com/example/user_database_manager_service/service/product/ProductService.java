package com.example.user_database_manager_service.service.product;

import com.example.user_database_manager_service.service.EntityService;
import org.jooq.DatePart;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService<P> extends EntityService<P> {
    Optional<P> findByUrl(URI url);

    Optional<P> findBy(UUID accountUuid, URI productUrl);

    List<P> findAllExpired(int timeLimit, DatePart datePart, int count);

    List<P> findAllBy(UUID accountUuid, int page, int count);

    boolean existsByUrl(URI url);

    boolean existsBy(UUID accountUuid, URI productUrl);

    void deleteByUrl(URI url);
}
