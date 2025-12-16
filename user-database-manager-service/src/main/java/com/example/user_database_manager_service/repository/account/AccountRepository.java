package com.example.user_database_manager_service.repository.account;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class AccountRepository<A> implements EntityRepository<A> {
    public abstract Optional<A> getById(Long id);

    public abstract Optional<A> getByUUID(UUID uuid);

    public abstract Optional<A> getByUserUUID(UUID uuid);

    public abstract Optional<A> getMainByUserUUID(UUID uuid);

    public abstract Optional<A> getMainByUserEmail(String email);

    public abstract Optional<A> getByAccountName(String accountName);
}
