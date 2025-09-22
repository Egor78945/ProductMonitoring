package com.example.security_manager_spring_boot_starter.service.token;

public interface SecurityManagerTokenParser {
    String validateAndGetAttribute(String token, String attribute);
}
