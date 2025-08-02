package com.example.authentication_service.service.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface AsyncTaskExecutorService {
    <T> CompletableFuture<T> submit(Supplier<T> task);

    CompletableFuture<Void> run(Runnable task);
}
