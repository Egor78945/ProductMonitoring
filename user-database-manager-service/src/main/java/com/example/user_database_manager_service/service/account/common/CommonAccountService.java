package com.example.user_database_manager_service.service.account.common;

import java.util.UUID;

public interface CommonAccountService {
    int getCountOfAccountsOfUserByUserUUID(UUID uuid);

    void deleteById(Long id);

    void deleteByAccountUuid(UUID accountUuid);

    void deleteByUserUuid(UUID uuid);

    void deleteByUserEmail(String email);

    boolean existsById(Long id);

    boolean existsByUUID(UUID uuid);

    boolean existsByUserUUID(UUID userUuuid);

    boolean existsByName(String name);
}
