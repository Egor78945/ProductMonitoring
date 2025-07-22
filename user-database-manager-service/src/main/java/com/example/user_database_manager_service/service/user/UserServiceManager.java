package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceManager implements UserService {
    private final UserEntityRepository userEntityRepository;

    public UserServiceManager(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UUID save(UserProtoConfiguration.UserMessage user) {
        if(!existsByEmail(user.getEmail())) {
            return userEntityRepository.save(user);
        } else throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.UserMessage findById(Long id) {
        return userEntityRepository.getById(id).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsById(Long id) {
        return userEntityRepository.existsById(id);
    }

    @Override
    public UserProtoConfiguration.UserMessage findByUUID(UUID uuid) {
        return userEntityRepository.getByUUID(uuid).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByEmail(String email) {
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
