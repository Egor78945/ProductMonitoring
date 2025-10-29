package com.example.user_database_manager_service.repository;

public interface EntityRepository<E> {
    E save(E entity);
    void delete(E entity);
}
