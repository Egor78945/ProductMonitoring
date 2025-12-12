package com.example.authentication_service.service.account;

import java.util.UUID;

public interface AccountService<A> {
    A getByUserUuid(UUID userUuid);
}
