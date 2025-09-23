package com.example.security_manager_spring_boot_starter.configuration.security.filter;

import com.example.security_manager_spring_boot_starter.service.keycloak.token.SecurityManagerKeycloakTokenParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityManagerRequestFilterConfiguration {
    @Bean
    public SecurityManagerJwtEmailExtractRequestFilter securityManagerJwtEmailExtractRequestFilter(SecurityManagerKeycloakTokenParser securityManagerKeycloakTokenParser) {
        return new SecurityManagerJwtEmailExtractRequestFilter(securityManagerKeycloakTokenParser);
    }
}
