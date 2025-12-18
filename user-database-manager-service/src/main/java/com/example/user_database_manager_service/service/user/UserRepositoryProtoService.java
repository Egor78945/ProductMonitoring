package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.UserRepository;
import com.example.user_database_manager_service.service.role.RoleService;
import com.example.user_database_manager_service.service.user.mapper.UserMapper;

import java.util.UUID;

public abstract class UserRepositoryProtoService extends UserRepositoryService<UserProtoConfiguration.UserMessage> {
    protected final RoleService<UserProtoConfiguration.RoleMessage> roleService;

    public UserRepositoryProtoService(UserRepository<UserProtoConfiguration.UserMessage> userRepository, RoleService<UserProtoConfiguration.RoleMessage> roleService) {
        super(userRepository);
        this.roleService = roleService;
    }

    @Override
    public UserProtoConfiguration.UserMessage findById(Long id) {
        UserProtoConfiguration.UserMessage user = super.findById(id);
        return UserMapper.addTo(user, roleService.getAllByUserEmail(user.getEmail()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByUUID(UUID uuid) {
        UserProtoConfiguration.UserMessage user = super.findByUUID(uuid);
        return UserMapper.addTo(user, roleService.getAllByUserEmail(user.getEmail()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByEmail(String email) {
        UserProtoConfiguration.UserMessage user = super.findByEmail(email);
        return UserMapper.addTo(user, roleService.getAllByUserEmail(user.getEmail()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByAccountName(String accountName) {
        UserProtoConfiguration.UserMessage user = super.findByAccountName(accountName);
        return UserMapper.addTo(user, roleService.getAllByUserEmail(user.getEmail()));
    }
}
