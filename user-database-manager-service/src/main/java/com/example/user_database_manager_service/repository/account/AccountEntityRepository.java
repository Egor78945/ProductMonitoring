package com.example.user_database_manager_service.repository.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class AccountEntityRepository extends EntityRepository<Long, UserProtoConfiguration.AccountMessage> {
    public abstract Optional<UserProtoConfiguration.AccountMessage> getByUUID(UUID uuid);
    public abstract Optional<UserProtoConfiguration.AccountMessage> getByUserUUID(UUID uuid);
    public abstract boolean existsByUserUUID(UUID uuid);
    public abstract UUID save(UserProtoConfiguration.AccountMessage entity);
}
