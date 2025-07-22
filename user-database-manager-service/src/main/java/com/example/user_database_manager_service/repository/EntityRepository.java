package com.example.user_database_manager_service.repository;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityRepository<ID extends Serializable, E> {
    public abstract Optional<E> getById(ID id);

    public abstract boolean existsById(ID id);

    public abstract boolean existsByUUID(UUID uuid);

    protected UUID generateUnbusyUUID() {
        int lap = 0;
        UUID uuid = UUID.randomUUID();
        while (lap < 10) {
            if (!existsByUUID(uuid)) {
                return uuid;
            }
            lap++;
        }
        throw new ProcessingException(ExceptionMessage.FAILED_TO_CREATE.getMessage());
    }
}
