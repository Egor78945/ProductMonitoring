package com.example.user_database_manager_service.repository;

import java.io.Serializable;
import java.util.Optional;

public interface EntityRepository <ID extends Serializable, E>{
    Optional<E> getById(ID id);
    void save(E entity);
    boolean existsById(ID id);
}
