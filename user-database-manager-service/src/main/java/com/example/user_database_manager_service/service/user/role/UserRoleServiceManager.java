package com.example.user_database_manager_service.service.user.role;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.role.UserRoleEntityRepository;
import org.springframework.beans.propertyeditors.UUIDEditor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceManager implements UserRoleService {
    private final UserRoleEntityRepository userRoleEntityRepository;

    public UserRoleServiceManager(UserRoleEntityRepository userRoleEntityRepository) {
        this.userRoleEntityRepository = userRoleEntityRepository;
    }

    @Override
    public List<UserProtoConfiguration.UserRoleMessage> findByUserUUID(UUID userUUID) {
        return userRoleEntityRepository.getUserRolesByUserUUID(userUUID);
    }

    @Override
    public void save(UserProtoConfiguration.UserRoleMessage userRole) {
        if(!userRoleEntityRepository.existsByUserUUIDAndRoleId(UUID.fromString(userRole.getUserUUID()), userRole.getRoleId())){
            userRoleEntityRepository.save(userRole);
        } else throw new NotFoundException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.UserRoleMessage findById(Long id) {
        return userRoleEntityRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsById(Long id) {
        return userRoleEntityRepository.existsById(id);
    }
}
