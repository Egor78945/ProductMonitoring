package com.example.user_database_manager_service.service.user.role;

import com.example.user_database_manager_service.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface UserRoleService<UR> extends EntityService<UR> {
    void deleteAllByUserUuid(UUID userUuid);

    void deleteAllByRoleId(Long roleId);

    UR findById(Long id);

    List<UR> findByUserUUID(UUID userUUID);

    boolean existsById(Long id);

    boolean existsBy(UUID userUuid, long roleId);
}
