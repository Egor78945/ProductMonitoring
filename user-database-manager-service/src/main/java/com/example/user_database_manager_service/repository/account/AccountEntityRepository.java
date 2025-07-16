package com.example.user_database_manager_service.repository.account;

import com.example.user_database_manager_service.model.account.entity.Account;
import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountEntityRepository extends EntityRepository<Long, Account> {
    Optional<Account> getByUUID(UUID uuid);
    Optional<Account> getByUserUUID(UUID uuid);
    boolean existsByUUID(UUID uuid);
}
