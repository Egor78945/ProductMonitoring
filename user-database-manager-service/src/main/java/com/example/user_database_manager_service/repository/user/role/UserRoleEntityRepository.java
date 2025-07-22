package com.example.user_database_manager_service.repository.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.user.role.entity.UserRole;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class UserRoleEntityRepository {
    public abstract Optional<UserProtoConfiguration.UserRoleMessage> getById(Long id);
    public abstract boolean existsById(Long id);
    public abstract List<UserProtoConfiguration.UserRoleMessage> getUserRolesByUserUUID(UUID userUUID);
    public abstract boolean existsByUserUUIDAndRoleId(UUID userUUID, Long roleId);
    public abstract void save(UserProtoConfiguration.UserRoleMessage userRoleMessage);
}