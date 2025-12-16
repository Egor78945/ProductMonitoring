package com.example.user_database_manager_service.service.account;

import com.example.user_database_manager_service.service.EntityService;

import java.util.UUID;

public interface AccountService<A> extends EntityService<A> {
    A findById(Long id);

    A findByUUID(UUID uuid);

    A findByUserUUID(UUID uuid);

    A findMainByUserUuid(UUID uuid);

    A findByAccountName(String accountName);
}
