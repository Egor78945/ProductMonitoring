package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public abstract class UserSupplyingRegistrationProtoService implements UserSupplyingRegistrationService<UserProtoConfiguration.UserRegistrationMessage> {
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService;
    protected final UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService;
    protected final AccountService<UserProtoConfiguration.AccountMessage> accountService;
    protected final AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService;

    public UserSupplyingRegistrationProtoService(UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService, UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRoleService = userRoleService;
        this.userNotificationService = userNotificationService;
        this.accountProductService = accountProductService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        UserProtoConfiguration.UserMessage savedUser = userService.existsByEmail(registerModel.getUser().getEmail()) ? userService.findByEmail(registerModel.getUser().getEmail()) : userService.save(registerModel.getUser());

        UserProtoConfiguration.AccountMessage accountMessage = registerModel.getAccount()
                .toBuilder()
                .setUserUuid(savedUser.getUuid())
                .build();

        UserProtoConfiguration.AccountMessage savedAccount = accountService.save(accountMessage);

        for (long roleId : registerModel.getUser().getUserRolesList()) {
            userRoleService.save(UserRoleMapper.map(roleId, UUID.fromString(savedUser.getUuid())));
        }

        return registerModel
                .toBuilder()
                .setUser(savedUser)
                .setAccount(savedAccount)
                .build();
    }

    @Override
    public void rollback(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        try {
            if (userService.existsById(registerModel.getUser().getId()) && LocalDateTime.now().getHour() - LocalDateTime.ofInstant(Instant.ofEpochMilli(userService.findById(registerModel.getUser().getId()).getRegisteredAt()), ZoneId.systemDefault()).getHour() <= 12) {
                accountProductService.deleteAllByAccountUuid(UUID.fromString(registerModel.getAccount().getUuid()));
                accountService.deleteByUserUuid(UUID.fromString(registerModel.getUser().getUuid()));
                userNotificationService.deleteAllByUserUuid(UUID.fromString(registerModel.getUser().getUuid()));
                userRoleService.deleteAllByUserUuid(UUID.fromString(registerModel.getUser().getUuid()));
                userService.deleteByUuid(UUID.fromString(registerModel.getUser().getUuid()));
            }
        } catch (Exception e) {
            throw new ProcessingException("failed to delete user");
        }
    }
}
