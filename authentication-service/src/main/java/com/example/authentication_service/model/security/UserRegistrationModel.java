package com.example.authentication_service.model.security;

import com.example.authentication_service.service.validation.annotation.Letter;
import com.example.authentication_service.service.validation.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class UserRegistrationModel {
    @NotEmpty
    @NotBlank
    @Email
    private final String email;
    @NotEmpty
    @NotBlank
    @Letter
    @Size(min = 5, max = 50)
    private final String name;
    @NotEmpty
    @NotBlank
    @Password
    private final String password;

    public UserRegistrationModel(String email, String name, String password) {
        this.email = email.toLowerCase();
        this.name = name.toLowerCase();
        this.password = password;
    }

    public String getName() {
        return name;
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
        return Objects.equals(email, that.email) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }

    @Override
    public String toString() {
        return "UserRegistrationModel{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
