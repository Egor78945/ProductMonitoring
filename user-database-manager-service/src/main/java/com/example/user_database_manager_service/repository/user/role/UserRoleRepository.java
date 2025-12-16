package com.example.user_database_manager_service.repository.user.role;

import com.example.user_database_manager_service.repository.EntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class UserRoleRepository<UR> implements EntityRepository<UR> {
    public abstract Optional<UR> getById(Long id);

    public abstract List<UR> getUserRolesByUserUUID(UUID userUUID);
}
