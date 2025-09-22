package com.example.security_manager_spring_boot_starter.configuration.web;

import com.example.security_manager_spring_boot_starter.configuration.security.keycloak.environment.SecurityManagerKeycloakEnvironment;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(SecurityManagerKeycloakEnvironment.class)
public class SecurityManagerWebClientConfiguration {
    @Bean
    public RestTemplate securityManagerStarterRestTemplate() {
        return new RestTemplate();
    }
}
