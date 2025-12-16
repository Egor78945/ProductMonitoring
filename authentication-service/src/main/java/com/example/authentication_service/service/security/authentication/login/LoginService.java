package com.example.authentication_service.service.security.authentication.login;

public interface LoginService<L, A> {
    A login(L loginModel);
}
