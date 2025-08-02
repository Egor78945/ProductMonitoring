package com.example.authentication_service.service.concurrency;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@Service
public class AsyncTaskExecutorServiceManager implements AsyncTaskExecutorService {
    private final ExecutorService executorService;

    public AsyncTaskExecutorServiceManager(@Qualifier("fixedThreadPoolExecutor") ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public <T> CompletableFuture<T> submit(Supplier<T> task) {
        return CompletableFuture.supplyAsync(task, executorService);
    }

    @Override
    public CompletableFuture<Void> run(Runnable task) {
        return CompletableFuture.runAsync(task, executorService);
    }
}
