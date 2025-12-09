package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;
import com.example.user_database_manager_service.service.user.UserService;

import java.util.UUID;

public abstract class UserRoleRepositoryProtoService extends UserRoleRepositoryService<UserProtoConfiguration.UserRoleMessage> {
    protected final UserService<?> userService;
    public UserRoleRepositoryProtoService(UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository, UserService<?> userService) {
        super(userRoleProtoRepository);
        this.userService = userService;
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
        if (userService.existsByUUID(UUID.fromString(userRole.getUserUUID())) && !existsBy(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())) {
            return super.save(userRole);
        }
        throw new AlreadyExistsException(String.format("UserRole is already exists by user uuid: %s", userRole));
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage update(UserProtoConfiguration.UserRoleMessage userRole) {
        if (existsBy(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())) {
            return super.update(userRole);
        }
        throw new NotFoundException(String.format("UserRole is not found by user uuid: %s", userRole));
    }
}
