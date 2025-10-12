package com.example.product_processor_service.repository;

import com.example.product_processor_service.util.function.Scrypt;

import java.util.List;

public abstract class Repository<T> {
    public abstract T save(T product);

    public abstract T update(T product);

    public abstract void saveAll(List<T> products);

    public abstract void transactional(Scrypt scrypt);
}
