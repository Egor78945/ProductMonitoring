package com.example.jwt_spring_boot_starter.service.jwt;

import com.example.jwt_spring_boot_starter.model.JwtSecret;

public class JwtServiceManager extends JwtService{
    public JwtServiceManager(JwtSecret jwtSecret) {
        super(jwtSecret);
    }
}
