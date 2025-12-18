package com.example.user_database_manager_service.repository.role;

import java.util.List;

public abstract class RoleRepository<R> {
    public abstract List<R> findByUserEmail(String email);
}
