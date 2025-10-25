package com.example.user_database_manager_service.repository.account;

import com.example.user_database_manager_service.repository.EntityRepository;
import com.example.user_database_manager_service.service.common.utils.UUIDManager;

import java.util.Optional;
import java.util.UUID;

public abstract class AccountRepository<A> extends UUIDManager implements EntityRepository<A> {
    public abstract Optional<A> getById(Long id);

    public abstract Optional<A> getByUUID(UUID uuid);

    public abstract Optional<A> getByUserUUID(UUID uuid);

    public abstract boolean existsByUUID(UUID uuid);

    public abstract boolean existsByUserUUID(UUID uuid);

    public abstract boolean existsByName(String name);

    public abstract boolean existsById(Long id);

    @Override
    public boolean isUnique(UUID uuid) {
        return existsByUUID(uuid);
    }
}
