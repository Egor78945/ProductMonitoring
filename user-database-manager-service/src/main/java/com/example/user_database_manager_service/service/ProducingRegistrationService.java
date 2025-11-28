package com.example.user_database_manager_service.service;

public interface ProducingRegistrationService<R> {
    R register(R userForm);
    void rollback(R userForm);
}
