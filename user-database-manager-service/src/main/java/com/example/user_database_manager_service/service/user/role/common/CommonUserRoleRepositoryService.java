package com.example.user_database_manager_service.service.user.role.common;

import com.example.user_database_manager_service.repository.user.role.common.CommonUserRoleRepository;

import java.util.UUID;

public abstract class CommonUserRoleRepositoryService implements CommonUserRoleService {
    protected final CommonUserRoleRepository commonUserRoleRepository;

    public CommonUserRoleRepositoryService(CommonUserRoleRepository commonUserRoleRepository) {
        this.commonUserRoleRepository = commonUserRoleRepository;
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        commonUserRoleRepository.deleteAllByUserUuid(userUuid);
    }

    @Override
    public void deleteAllByRoleId(Long roleId) {
        commonUserRoleRepository.deleteAllByRoleId(roleId);
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        commonUserRoleRepository.deleteAllByUserEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return commonUserRoleRepository.existsById(id);
    }

    @Override
    public boolean existsBy(UUID userUuid, long roleId) {
        return commonUserRoleRepository.existsBy(userUuid, roleId);
    }
}
