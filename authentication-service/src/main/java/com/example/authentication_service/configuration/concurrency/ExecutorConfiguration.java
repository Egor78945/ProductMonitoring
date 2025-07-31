package com.example.authentication_service.configuration.concurrency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfiguration {
    @Bean
    public ExecutorService fixedThreadPoolExecutor() {
        return Executors.newFixedThreadPool(20, r -> new Thread(() -> {
            Thread.currentThread().setContextClassLoader(ExecutorConfiguration.class.getClassLoader());
            r.run();
        }));
    }
}
