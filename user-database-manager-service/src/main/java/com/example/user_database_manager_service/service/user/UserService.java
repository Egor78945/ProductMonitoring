package com.example.user_database_manager_service.service.user;

import com.example.user_database_manager_service.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface UserService<U> extends EntityService<U> {
    U findById(Long id);

    U findByUUID(UUID uuid);

    U findByEmail(String email);

    U findByAccountName(String accountName);

    boolean existsBy(Long id, UUID uuid, String email);

    boolean existsById(Long id);

    boolean existsByUUID(UUID uuid);

    boolean existsByEmail(String email);
}
