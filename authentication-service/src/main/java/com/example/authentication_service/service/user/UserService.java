package com.example.authentication_service.service.user;

public interface UserService<U> {
    U getByAccountName(String accountName);
    U getByEmail(String email);
}
