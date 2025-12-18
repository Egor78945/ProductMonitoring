package com.example.user_database_manager_service.service.role;

import com.example.user_database_manager_service.repository.role.RoleRepository;

import java.util.List;

public abstract class RoleRepositoryService<R> implements RoleService<R>{
    protected final RoleRepository<R> roleRepository;

    public RoleRepositoryService(RoleRepository<R> roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<R> getAllByUserEmail(String email) {
        return roleRepository.findByUserEmail(email);
    }
}
