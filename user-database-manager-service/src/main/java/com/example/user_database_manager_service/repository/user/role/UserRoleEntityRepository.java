package com.example.user_database_manager_service.repository.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.user.role.entity.UserRole;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleEntityRepository extends EntityRepository<Long, UserProtoConfiguration.UserRoleMessage> {
    List<UserProtoConfiguration.UserRoleMessage> getUserRolesByUserUUID(UUID userUUID);
    boolean existsByUserUUIDAndRoleId(UUID userUUID, Long roleId);
    void save(UserProtoConfiguration.UserRoleMessage userRoleMessage);
}