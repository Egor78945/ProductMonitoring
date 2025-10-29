package com.example.user_database_manager_service.service.authentication;

public interface AuthenticationService<R> {
    R register(R userForm);
    void delete(R userForm);
}
