package com.example.user_database_manager_service.repository.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends EntityRepository<Long, UserProtoConfiguration.UserMessage>{
    Optional<UserProtoConfiguration.UserMessage> getByUUID(UUID uuid);

    Optional<UserProtoConfiguration.UserMessage> getByEmail(String email);

    boolean existsByUUID(UUID uuid);

    boolean existsByEmail(String email);

    UUID save(UserProtoConfiguration.UserMessage entity);
}
