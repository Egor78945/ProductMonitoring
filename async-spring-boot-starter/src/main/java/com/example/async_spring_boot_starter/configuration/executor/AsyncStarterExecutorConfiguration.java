package com.example.async_spring_boot_starter.configuration.executor;

import com.example.async_spring_boot_starter.configuration.AsyncStarterEnvironment;
import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorServiceManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(AsyncStarterEnvironment.class)
public class AsyncStarterExecutorConfiguration {
    private final AsyncStarterEnvironment asyncStarterEnvironment;

    public AsyncStarterExecutorConfiguration(AsyncStarterEnvironment asyncStarterEnvironment) {
        this.asyncStarterEnvironment = asyncStarterEnvironment;
    }

    @Bean
    public ExecutorService asyncStarterFixedThreadPoolExecutor() {
        return Executors.newFixedThreadPool(asyncStarterEnvironment.getThreadPoolSize(), r -> new Thread(() -> {
            Thread.currentThread().setContextClassLoader(AsyncStarterExecutorConfiguration.class.getClassLoader());
            r.run();
        }));
    }

    @Bean
    public AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorServiceManager(@Qualifier("asyncStarterFixedThreadPoolExecutor") ExecutorService executorService) {
        return new AsyncStarterAsyncTaskExecutorServiceManager(executorService);
    }
}
