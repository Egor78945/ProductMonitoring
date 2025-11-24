package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.UserRepository;

import java.util.UUID;

public abstract class UserProtoService implements UserService<UserProtoConfiguration.UserMessage> {
    protected final UserRepository<UserProtoConfiguration.UserMessage> userProtoRepository;

    public UserProtoService(UserRepository<UserProtoConfiguration.UserMessage> userProtoRepository) {
        this.userProtoRepository = userProtoRepository;
    }

    @Override
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage user) {
        return userProtoRepository.save(user);
    }

    @Override
    public UserProtoConfiguration.UserMessage update(UserProtoConfiguration.UserMessage entity) {
        return userProtoRepository.update(entity);
    }

    @Override
    public UserProtoConfiguration.UserMessage findById(Long id) {
        return userProtoRepository.getById(id).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByUUID(UUID uuid) {
        return userProtoRepository.getByUUID(uuid).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByEmail(String email) {
        return userProtoRepository.getByEmail(email).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public UserProtoConfiguration.UserMessage findByAccountName(String accountName) {
        return userProtoRepository.getByAccountName(accountName).orElseThrow(() -> new ProcessingException(ExceptionMessage.NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteById(Long id) {
        userProtoRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        userProtoRepository.deleteByUuid(uuid);
    }

    @Override
    public boolean existsBy(Long id, UUID uuid, String email) {
        return userProtoRepository.existsBy(id, uuid, email);
    }

    @Override
    public boolean existsById(Long id) {
        return userProtoRepository.existsById(id);
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return userProtoRepository.existsByUUID(uuid);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userProtoRepository.existsByEmail(email);
    }

}
