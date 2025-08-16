package com.example.product_processor_service.repository.account;

import com.example.product_processor_service.model.account.entity.Account;

import java.util.UUID;

public abstract class AccountRepository <A extends Account>{
    public abstract boolean existsByUuid(UUID uuid);
}
