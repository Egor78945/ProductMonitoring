package com.example.user_database_manager_service.service;

import java.io.Serializable;

public interface EntityService <ID extends Serializable, E> {
    E findById(ID id);
    boolean existsById(ID id);
}
