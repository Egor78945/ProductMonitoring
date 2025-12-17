package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import com.example.user_database_manager_service.service.user.role.mapper.UserRoleMapper;

import java.util.UUID;

public abstract class UserSupplyingRegistrationProtoService implements UserSupplyingRegistrationService<UserProtoConfiguration.UserRegistrationMessage, String> {
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final CommonUserService commonUserService;

    protected final UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService;
    protected final AccountService<UserProtoConfiguration.AccountMessage> accountService;

    public UserSupplyingRegistrationProtoService(UserService<UserProtoConfiguration.UserMessage> userService, CommonUserService commonUserService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService) {
        this.userService = userService;
        this.commonUserService = commonUserService;
        this.accountService = accountService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        boolean exists = commonUserService.existsByEmail(registerModel.getUser().getEmail());
        UserProtoConfiguration.UserMessage savedUser = exists ? userService.findByEmail(registerModel.getUser().getEmail()) : userService.save(registerModel.getUser());

        UserProtoConfiguration.AccountMessage accountMessage = registerModel.getAccount()
                .toBuilder()
                .setUserUuid(savedUser.getUuid())
                .setMain(!exists)
                .build();

        UserProtoConfiguration.AccountMessage savedAccount = accountService.save(accountMessage);

        if (!exists) {
            for (UserProtoConfiguration.RoleMessage role : registerModel.getUser().getUserRolesList()) {
                userRoleService.save(UserRoleMapper.map(role.getRoleId(), UUID.fromString(savedUser.getUuid())));
            }
        }

        return registerModel
                .toBuilder()
                .setUser(savedUser)
                .setAccount(savedAccount)
                .setFirst(!exists)
                .build();
    }

    @Override
    public void deleteByEmail(String userEmail) {
        commonUserService.deleteByEmail(userEmail);
    }
}
