package com.example.async_spring_boot_starter.configuration.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncStarterExecutorConfiguration {
    @Bean
    public ExecutorService asyncStarterFixedThreadPoolExecutor() {
        return Executors.newFixedThreadPool(20, r -> new Thread(() -> {
            Thread.currentThread().setContextClassLoader(AsyncStarterExecutorConfiguration.class.getClassLoader());
            r.run();
        }));
    }
}
