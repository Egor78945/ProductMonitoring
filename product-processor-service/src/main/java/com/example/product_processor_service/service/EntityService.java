package com.example.product_processor_service.service;

public interface EntityService<E> {
    E save(E entity);
    E update(E entity);
    void delete(E entity);
}
