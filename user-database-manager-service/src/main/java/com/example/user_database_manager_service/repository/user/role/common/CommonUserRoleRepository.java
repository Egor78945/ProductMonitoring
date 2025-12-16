package com.example.user_database_manager_service.repository.user.role.common;

import java.util.UUID;

public interface CommonUserRoleRepository {
    boolean existsById(Long id);

    boolean existsBy(UUID userUUID, Long roleId);

    void deleteAllByUserUuid(UUID userUuid);

    void deleteAllByRoleId(Long roleId);

    void deleteAllByUserEmail(String email);
}
