package com.example.user_database_manager_service.repository.user.role;

import com.example.user_database_manager_service.model.user.role.entity.UserRole;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleEntityRepository extends EntityRepository<Long, UserRole> {
    List<UserRole> getUserRolesByUserUUID(UUID userUUID);
    boolean existsByUserUUIDAndRoleId(UUID userUUID, Long roleId);
}