package com.example.authentication_service.service.security.authentication;

public interface AuthenticationService<T, R> {
    T login(String username, String password);
    void register(R registerModel);
}
