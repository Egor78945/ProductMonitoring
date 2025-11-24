package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.role.JooqUserRoleRepository;
import com.example.user_database_manager_service.repository.user.role.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRoleServiceManager extends UserRoleProtoService {
    public UserRoleServiceManager(UserRoleRepository<UserProtoConfiguration.UserRoleMessage> userRoleProtoRepository) {
        super(userRoleProtoRepository);
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage save(UserProtoConfiguration.UserRoleMessage userRole) {
        if (!userRoleProtoRepository.existsBy(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())) {
            return super.save(userRole);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage update(UserProtoConfiguration.UserRoleMessage userRole) {
        if (userRoleProtoRepository.existsBy(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())) {
            return super.update(userRole);
        }
        throw new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}
