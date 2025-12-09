package com.example.user_database_manager_service.service;

public interface EntityService<E> {
    E save(E entity);
    E update(E entity);
}
