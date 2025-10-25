package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;

import java.util.UUID;

public abstract class UserAuthenticationProtoService implements UserAuthenticationService<UserProtoConfiguration.UserRegistrationMessage> {
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService;
    protected final UserNotificationService<UserNotification>  userNotificationService;
    protected final AccountService<UserProtoConfiguration.AccountMessage> accountService;
    protected final AccountProductService<AccountProduct> accountProductService;

    public UserAuthenticationProtoService(UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService, UserNotificationService<UserNotification> userNotificationService, AccountProductService<AccountProduct> accountProductService) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRoleService = userRoleService;
        this.userNotificationService = userNotificationService;
        this.accountProductService = accountProductService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
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
    }

    @Override
    public void delete(UserProtoConfiguration.UserRegistrationMessage userForm) {
        accountProductService.deleteAllByAccountUuid(UUID.fromString(userForm.getAccount().getUuid()));
        accountService.delete(userForm.getAccount());
        userNotificationService.deleteAllByUserUuid(UUID.fromString(userForm.getUser().getUuid()));
        userRoleService.deleteAllByUserUuid(UUID.fromString(userForm.getUser().getUuid()));
        userService.delete(userForm.getUser());
    }
}
