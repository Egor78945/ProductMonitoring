package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRoleRepositoryProtoServiceManager extends UserRoleRepositoryService<UserProtoConfiguration.UserRoleMessage> {
    public UserRoleRepositoryProtoServiceManager(UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository) {
        super(userRoleProtoRepository);
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
        if (!existsBy(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())) {
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
