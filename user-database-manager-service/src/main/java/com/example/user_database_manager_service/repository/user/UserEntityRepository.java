package com.example.user_database_manager_service.repository.user;

import com.example.user_database_manager_service.model.user.entity.User;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository<ID extends Serializable, U extends User> {
    Optional<U> getById(ID id);
    void save(U entity);
    boolean existsById(ID id);
    Optional<User> getByUUID(UUID uuid);

    Optional<User> getByEmail(String email);

    boolean existsByUUID(UUID uuid);

    boolean existsByEmail(String email);
}
