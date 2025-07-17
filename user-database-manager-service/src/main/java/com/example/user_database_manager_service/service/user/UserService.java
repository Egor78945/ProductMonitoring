package com.example.user_database_manager_service.service.user;

import com.example.user_database_manager_service.model.user.entity.User;
import com.example.user_database_manager_service.service.EntityService;

import java.util.UUID;

public interface UserService extends EntityService<Long,User> {
    User findByUUID(UUID uuid);
    User findByEmail(String email);
    boolean existsByUUID(UUID uuid);
    boolean existsByEmail(String email);
}
