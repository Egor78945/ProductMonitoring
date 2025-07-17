package com.example.user_database_manager_service.service.user.role;

import com.example.user_database_manager_service.model.user.role.entity.UserRole;
import com.example.user_database_manager_service.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface UserRoleService extends EntityService<Long, UserRole> {
    List<UserRole> findByUserUUID(UUID userUUID);
}
