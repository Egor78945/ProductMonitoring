package com.example.user_database_manager_service.service.user.authentication;

import com.example.user_database_manager_service.service.SupplyingRegistrationService;

public interface UserSupplyingRegistrationService<S,D> extends SupplyingRegistrationService<S> {
    void deleteByEmail(D subject);
}
