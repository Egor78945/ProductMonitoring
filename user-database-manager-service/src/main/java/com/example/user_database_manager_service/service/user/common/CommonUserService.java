package com.example.user_database_manager_service.service.user.common;

import java.util.UUID;

public interface CommonUserService {
    void deleteById(Long id);

    void deleteByUuid(UUID uuid);

    void deleteByEmail(String email);

    boolean existsBy(Long id, UUID uuid, String email);

    boolean existsById(Long id);

    boolean existsByUUID(UUID uuid);

    boolean existsByEmail(String email);
}
