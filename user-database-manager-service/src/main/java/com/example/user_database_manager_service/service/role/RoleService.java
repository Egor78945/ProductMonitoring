package com.example.user_database_manager_service.service.role;

import java.util.List;

public interface RoleService<R> {
    List<R> getAllByUserEmail(String email);
}
