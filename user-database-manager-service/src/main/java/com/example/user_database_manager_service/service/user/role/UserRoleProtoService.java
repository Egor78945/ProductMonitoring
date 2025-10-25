package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.role.UserRoleProtoRepository;

import java.util.List;
import java.util.UUID;

public abstract class UserRoleProtoService implements UserRoleService<UserProtoConfiguration.UserRoleMessage> {
    protected final UserRoleProtoRepository userRoleProtoRepository;

    public UserRoleProtoService(UserRoleProtoRepository userRoleProtoRepository) {
        this.userRoleProtoRepository = userRoleProtoRepository;
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
        if (!userRoleProtoRepository.existsBy(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())) {
            return userRoleProtoRepository.save(userRole);
        } else throw new NotFoundException(ExceptionMessage.ALREADY_EXISTS.getMessage());
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
