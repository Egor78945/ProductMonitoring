package com.example.user_database_manager_service.repository.user.common;

import java.util.UUID;

public interface CommonUserRepository {
    void deleteById(Long id);

    void deleteByUuid(UUID uuid);

    void deleteByEmail(String email);

    boolean existsBy(Long id, UUID uuid, String email);

    boolean existsByEmail(String email);

    boolean existsByUUID(UUID uuid);

    boolean existsById(Long id);
}
