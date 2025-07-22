package com.example.user_database_manager_service.repository.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class UserEntityRepository extends EntityRepository<Long, UserProtoConfiguration.UserMessage>{
    public abstract Optional<UserProtoConfiguration.UserMessage> getByUUID(UUID uuid);

    public abstract Optional<UserProtoConfiguration.UserMessage> getByEmail(String email);

    public abstract boolean existsByEmail(String email);

    public abstract UUID save(UserProtoConfiguration.UserMessage entity);
}
