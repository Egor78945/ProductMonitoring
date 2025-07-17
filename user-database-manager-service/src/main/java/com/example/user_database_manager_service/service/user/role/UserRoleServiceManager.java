package com.example.user_database_manager_service.service.user.role;

import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.model.user.role.entity.UserRole;
import com.example.user_database_manager_service.repository.user.role.UserRoleEntityRepository;
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
    public List<UserRole> findByUserUUID(UUID userUUID) {
        return userRoleEntityRepository.getUserRolesByUserUUID(userUUID);
    }

    @Override
    public void save(UserRole userRole) {
        if(userRoleEntityRepository.existsByUserUUIDAndRoleId(userRole.getUserUUID(), userRole.getRoleId())){
            userRoleEntityRepository.save(userRole);
        } else throw new NotFoundException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserRole findById(Long id) {
        return userRoleEntityRepository.getById(id).orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsById(Long id) {
        return userRoleEntityRepository.existsById(id);
    }
}
