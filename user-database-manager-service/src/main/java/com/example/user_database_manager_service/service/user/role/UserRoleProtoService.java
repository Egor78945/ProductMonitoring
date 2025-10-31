package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.role.JooqUserRoleRepository;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;

import java.util.List;
import java.util.UUID;

public abstract class UserRoleProtoService implements UserRoleService<UserProtoConfiguration.UserRoleMessage> {
    protected final UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository;

    public UserRoleProtoService(UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository) {
        this.userRoleProtoRepository = userRoleProtoRepository;
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
            return userRoleProtoRepository.save(userRole);
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage update(UserProtoConfiguration.UserRoleMessage entity) {
        return userRoleProtoRepository.update(entity);
    }

    @Override
    public void delete(UserProtoConfiguration.UserRoleMessage userRole) {
        userRoleProtoRepository.delete(userRole);
    }

    @Override
    public void deleteAllByRoleId(Long roleId) {
        userRoleProtoRepository.deleteAllByRoleId(roleId);
    }

    @Override
    public void deleteAllByUserUuid(UUID userUuid) {
        userRoleProtoRepository.deleteAllByUserUuid(userUuid);
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage findById(Long id) {
        return userRoleProtoRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public List<UserProtoConfiguration.UserRoleMessage> findByUserUUID(UUID userUUID) {
        return userRoleProtoRepository.getUserRolesByUserUUID(userUUID);
    }

    @Override
    public boolean existsById(Long id) {
        return userRoleProtoRepository.existsById(id);
    }
}
