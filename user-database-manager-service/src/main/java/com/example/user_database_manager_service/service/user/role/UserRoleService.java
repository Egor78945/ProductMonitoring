package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface UserRoleService extends EntityService<Long, UserProtoConfiguration.UserRoleMessage> {
    List<UserProtoConfiguration.UserRoleMessage> findByUserUUID(UUID userUUID);
}
