package com.example.user_database_manager_service.repository.account.common;

import java.util.UUID;

public interface CommonAccountRepository {
    int getCountOfAccountsOfUserByUserUUID(UUID uuid);

    void deleteById(Long id);

    void deleteByAccountUuid(UUID accountUuid);

    void deleteByUserUuid(UUID userUuid);

    void deleteByUserEmail(String email);

    boolean existsByUUID(UUID uuid);

    boolean existsByUserUUID(UUID uuid);

    boolean existsByName(String name);

    boolean existsById(Long id);
}
