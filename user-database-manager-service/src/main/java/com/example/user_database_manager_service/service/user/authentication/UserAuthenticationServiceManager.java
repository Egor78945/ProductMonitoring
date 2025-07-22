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
            String savedUserUuid = userService.save(registerModel.getUser()).toString();
            UserProtoConfiguration.AccountMessage accountMessage = registerModel.getAccount();
            accountMessage = accountMessage.toBuilder()
                    .setUserUuid(savedUserUuid)
                    .build();
            accountService.save(accountMessage);
            for (long roleId : registerModel.getUser().getUserRolesList()) {
                UserProtoConfiguration.UserRoleMessage role = UserProtoConfiguration.UserRoleMessage
                        .newBuilder()
                        .setRoleId(roleId)
                        .setUserUUID(savedUserUuid)
                        .build();
                userRoleService.save(role);
            }
    }
}
