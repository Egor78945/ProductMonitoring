package com.example.authentication_service.service.security.authentication.register;

public interface RegistrationService<R,A> {
    A register(R registerModel);
}
