package com.example.user_database_manager_service.service.user.role.common;

import java.util.UUID;

public interface CommonUserRoleService {
    void deleteAllByUserUuid(UUID userUuid);

    void deleteAllByRoleId(Long roleId);

    void deleteAllByUserEmail(String email);

    boolean existsById(Long id);

    boolean existsBy(UUID userUuid, long roleId);
}
