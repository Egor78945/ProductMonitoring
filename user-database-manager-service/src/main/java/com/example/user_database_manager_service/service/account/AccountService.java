package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.EntityService;

import java.util.UUID;

public interface AccountService extends EntityService<Long, UserProtoConfiguration.AccountMessage> {
    UUID save(UserProtoConfiguration.AccountMessage entity);
    UserProtoConfiguration.AccountMessage findByUUID(UUID uuid);
    UserProtoConfiguration.AccountMessage findByUserUUID(UUID uuid);
    boolean existsByUserUUID(UUID uuid);
    boolean existsByUUID(UUID uuid);
}
