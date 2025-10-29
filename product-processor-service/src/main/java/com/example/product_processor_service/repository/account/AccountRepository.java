package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AccountRepository<A> extends Repository<A> {
    public abstract Optional<A> findByUserEmail(String email);

    public abstract List<A> findAllByUuid(UUID uuid);

    public abstract boolean existsByUuid(UUID uuid);
}
