package com.example.authentication_service.service.security.authentication;

public interface RegistrationService<R,A> {
    A register(R registerModel);
}
