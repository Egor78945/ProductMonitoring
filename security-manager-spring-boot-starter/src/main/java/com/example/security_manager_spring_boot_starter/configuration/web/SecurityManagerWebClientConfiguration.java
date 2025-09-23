package com.example.security_manager_spring_boot_starter.configuration.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityManagerWebClientConfiguration {
    @Bean
    public RestTemplate securityManagerStarterRestTemplate() {
        return new RestTemplate();
    }
}
