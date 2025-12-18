package com.example.authentication_service.service.security.authentication;

public interface LoginService<L, A> {
    A login(L loginModel);
}
