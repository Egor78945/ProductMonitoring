package com.example.user_database_manager_service.repository.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountEntityRepository extends EntityRepository<Long, UserProtoConfiguration.AccountMessage> {
    Optional<UserProtoConfiguration.AccountMessage> getByUUID(UUID uuid);
    Optional<UserProtoConfiguration.AccountMessage> getByUserUUID(UUID uuid);
    boolean existsByUUID(UUID uuid);
    boolean existsByUserUUID(UUID uuid);
    UUID save(UserProtoConfiguration.AccountMessage entity);
}
