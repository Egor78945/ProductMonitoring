package com.example.product_processor_service.service.account;

import com.example.product_processor_service.model.account.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountService <A extends Account>{
    boolean existsByUuid(UUID uuid);
    Optional<A> getByUserEmail(String email);
}
