package com.example.user_database_manager_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class UserDatabaseManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDatabaseManagerServiceApplication.class, args);
    }

}
