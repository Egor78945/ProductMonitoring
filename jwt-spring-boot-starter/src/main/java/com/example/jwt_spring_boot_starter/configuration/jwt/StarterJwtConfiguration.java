package com.example.jwt_spring_boot_starter.configuration.jwt;

import com.example.jwt_spring_boot_starter.model.JwtSecret;
import com.example.jwt_spring_boot_starter.service.jwt.JwtService;
import com.example.jwt_spring_boot_starter.service.jwt.JwtServiceManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtSecret.class)
public class StarterJwtConfiguration {
    @Bean
    public JwtService jwtService(JwtSecret jwtSecret) {
        return new JwtServiceManager(jwtSecret);
    }
}
