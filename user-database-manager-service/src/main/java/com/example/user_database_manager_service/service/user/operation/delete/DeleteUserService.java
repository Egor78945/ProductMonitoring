package com.example.user_database_manager_service.service.user.operation.delete;

import java.util.UUID;

public interface DeleteUserService {
    void deleteById(Long id);

    void deleteByUuid(UUID uuid);

    void deleteByEmail(String email);
}
