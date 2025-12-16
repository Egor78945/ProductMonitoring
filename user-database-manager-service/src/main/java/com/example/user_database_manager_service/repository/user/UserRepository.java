package com.example.user_database_manager_service.repository.user;

import com.example.user_database_manager_service.repository.EntityRepository;
import com.example.user_database_manager_service.service.common.utils.UUIDManager;

import java.util.Optional;
import java.util.UUID;

public abstract class UserRepository<U> implements EntityRepository<U> {
    public abstract Optional<U> getById(Long id);

    public abstract Optional<U> getByUUID(UUID uuid);

    public abstract Optional<U> getByEmail(String email);

    public abstract Optional<U> getByAccountName(String accountName);
}
