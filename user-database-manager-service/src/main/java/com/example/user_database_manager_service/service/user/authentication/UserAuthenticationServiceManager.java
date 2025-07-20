package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthenticationServiceManager implements UserAuthenticationService<UserProtoConfiguration.UserRegistrationMessage> {
    private final UserService userService;
    private final AccountService accountService;
    private final UserRoleService userRoleService;

    public UserAuthenticationServiceManager(UserService userService, AccountService accountService, UserRoleService userRoleService) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        if (canBeSaved(registerModel.getUser()) && canBeSaved(registerModel.getAccount())) {
            userService.save(registerModel.getUser());
            accountService.save(registerModel.getAccount());
            for (UserProtoConfiguration.UserRoleMessage role : registerModel.getUserRolesList()) {
                userRoleService.save(role);
            }
        } else throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    public boolean canBeSaved(UserProtoConfiguration.AccountMessage account) {
        return account.getId() == 0 && !accountService.existsByUUID(UUID.fromString(account.getUuid())) && !accountService.existsByUserUUID(UUID.fromString(account.getUserUuid()));
    }

    public boolean canBeSaved(UserProtoConfiguration.UserMessage user) {
        return user.getId() == 0 && !userService.existsByEmail(user.getEmail()) && !userService.existsByUUID(UUID.fromString(user.getUuid()));
    }
}
