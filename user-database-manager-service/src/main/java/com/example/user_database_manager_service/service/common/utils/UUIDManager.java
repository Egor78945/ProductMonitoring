package com.example.user_database_manager_service.service.common.utils;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;

import java.util.UUID;

public abstract class UUIDManager {
    public UUID build() {
        int lap = 0;
        UUID uuid = UUID.randomUUID();
        while (lap < 10) {
            if (!isUnique(uuid)) {
                return uuid;
            }
            lap++;
        }
        throw new ProcessingException(ExceptionMessage.FAILED_TO_CREATE.getMessage());
    }
    public abstract boolean isUnique(UUID uuid);
}
