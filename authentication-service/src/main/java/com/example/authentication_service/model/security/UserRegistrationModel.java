package com.example.authentication_service.model.security;

import com.example.authentication_service.service.validation.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class UserRegistrationModel {
    @NotEmpty
    @NotBlank
    @Email
    private final String email;
    @NotEmpty
    @NotBlank
    @Password
    private final String password;

    public UserRegistrationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationModel that = (UserRegistrationModel) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "UserRegistrationModel{" +
                "email='" + email + '\'' +
                '}';
    }
}
