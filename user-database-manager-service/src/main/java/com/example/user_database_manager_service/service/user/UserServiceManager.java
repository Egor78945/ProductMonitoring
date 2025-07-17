package com.example.user_database_manager_service.service.user;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.model.user.entity.User;
import com.example.user_database_manager_service.repository.user.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceManager implements UserService {
    private final UserEntityRepository<Long, User> userEntityRepository;

    public UserServiceManager(UserEntityRepository<Long, User> userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void save(User user) {
        if(!existsByEmail(user.getEmail()) && !existsByUUID(user.getUuid())) {
            userEntityRepository.save(user);
        } else throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public User findById(Long id) {
        return userEntityRepository.getById(id).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsById(Long id) {
        return userEntityRepository.existsById(id);
    }

    @Override
    public User findByUUID(UUID uuid) {
        return userEntityRepository.getByUUID(uuid).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public User findByEmail(String email) {
        return userEntityRepository.getByEmail(email).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return userEntityRepository.existsByUUID(uuid);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userEntityRepository.existsByEmail(email);
    }
}
