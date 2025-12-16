package com.example.user_database_manager_service.service.user;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.UserRepository;

import java.util.UUID;

public abstract class UserRepositoryService<U> implements UserService<U>{
    protected final UserRepository<U> userRepository;

    public UserRepositoryService(UserRepository<U> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public U save(U user) {
        return userRepository.save(user);
    }

    @Override
    public U update(U entity) {
        return userRepository.update(entity);
    }

    @Override
    public U findById(Long id) {
        return userRepository.getById(id).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public U findByUUID(UUID uuid) {
        return userRepository.getByUUID(uuid).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public U findByEmail(String email) {
        return userRepository.getByEmail(email).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public U findByAccountName(String accountName) {
        return userRepository.getByAccountName(accountName).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }
}
