package com.example.product_processor_service.repository.user;

import com.example.product_processor_service.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class UserRepository<U> extends Repository<U> {
    public abstract Optional<U> findByUuid(UUID uuid);

    public abstract List<U> findAllByProductUrl(String url);
}
