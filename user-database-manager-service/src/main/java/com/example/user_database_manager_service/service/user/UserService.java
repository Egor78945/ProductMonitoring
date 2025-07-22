package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.EntityService;

import java.util.UUID;

public interface UserService extends EntityService<Long, UserProtoConfiguration.UserMessage> {
    UUID save(UserProtoConfiguration.UserMessage entity);
    UserProtoConfiguration.UserMessage findByUUID(UUID uuid);
    UserProtoConfiguration.UserMessage findByEmail(String email);
    boolean existsByUUID(UUID uuid);
    boolean existsByEmail(String email);
}
