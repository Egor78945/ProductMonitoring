package com.example.security_manager_spring_boot_starter.configuration.service;

import com.example.security_manager_spring_boot_starter.configuration.security.keycloak.environment.SecurityManagerKeycloakEnvironment;
import com.example.security_manager_spring_boot_starter.service.keycloak.SecurityManagerKeycloakService;
import com.example.security_manager_spring_boot_starter.service.keycloak.SecurityManagerKeycloakServiceManager;
import com.example.security_manager_spring_boot_starter.service.keycloak.token.SecurityManagerKeycloakTokenParser;
import com.example.security_manager_spring_boot_starter.service.keycloak.token.SecurityManagerKeycloakTokenParserManager;
import com.example.security_manager_spring_boot_starter.service.web.SecurityManagerWebClientService;
import com.example.security_manager_spring_boot_starter.service.web.SecurityManagerWebClientServiceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityManagerServiceConfiguration {
    @Bean
    public SecurityManagerWebClientService securityManagerWebClientService(RestTemplate restTemplate) {
        return new SecurityManagerWebClientServiceManager(restTemplate);
    }

    @Bean
    public SecurityManagerKeycloakService securityManagerKeycloakService(SecurityManagerKeycloakEnvironment keycloakEnvironment, SecurityManagerWebClientService securityManagerWebClientService) {
        return new SecurityManagerKeycloakServiceManager(keycloakEnvironment, securityManagerWebClientService);
    }

    @Bean
    public SecurityManagerKeycloakTokenParser securityManagerKeycloakTokenParserManager(SecurityManagerKeycloakService securityManagerKeycloakService) {
        return new SecurityManagerKeycloakTokenParserManager(securityManagerKeycloakService);
    }
}
