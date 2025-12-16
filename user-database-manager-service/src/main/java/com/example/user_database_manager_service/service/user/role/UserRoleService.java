package com.example.user_database_manager_service.service.user.role;

import com.example.user_database_manager_service.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface UserRoleService<UR> extends EntityService<UR> {
    UR findById(Long id);

    List<UR> findByUserUUID(UUID userUUID);
}
