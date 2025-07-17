package com.example.user_database_manager_service.service.account;

import com.example.user_database_manager_service.model.account.entity.Account;
import com.example.user_database_manager_service.service.EntityService;

import java.util.UUID;

public interface AccountService extends EntityService<Long, Account> {
    Account findByUUID(UUID uuid);
    Account findByUserUUID(UUID uuid);
    boolean existsByUserUUID(UUID uuid);
}
