package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;

import java.util.UUID;

public abstract class UserProducingRegistrationProtoService implements UserProducingRegistrationService<UserProtoConfiguration.UserRegistrationMessage> {
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService;
    protected final UserNotificationService<UserProtoConfiguration.UserNotificationMessage>  userNotificationService;
    protected final AccountService<UserProtoConfiguration.AccountMessage> accountService;
    protected final AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService;

    public UserProducingRegistrationProtoService(UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService, UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRoleService = userRoleService;
        this.userNotificationService = userNotificationService;
        this.accountProductService = accountProductService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        try {
            UserProtoConfiguration.UserMessage savedUser = userService.save(registerModel.getUser());

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
        } catch (Exception e) {
            rollback(registerModel);
            throw new ProcessingException("failed to register user");
        }
    }

    @Override
    public void rollback(UserProtoConfiguration.UserRegistrationMessage userForm) {
        accountProductService.deleteAllByAccountUuid(UUID.fromString(userForm.getAccount().getUuid()));
        accountService.deleteById(userForm.getAccount().getId());
        userNotificationService.deleteAllByUserUuid(UUID.fromString(userForm.getUser().getUuid()));
        userRoleService.deleteAllByUserUuid(UUID.fromString(userForm.getUser().getUuid()));
        userService.deleteById(userForm.getUser().getId());
    }
}
