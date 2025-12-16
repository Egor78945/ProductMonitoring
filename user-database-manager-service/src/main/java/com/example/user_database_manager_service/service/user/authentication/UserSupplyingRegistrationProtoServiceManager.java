package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserSupplyingRegistrationProtoServiceManager extends UserSupplyingRegistrationProtoService {
    public UserSupplyingRegistrationProtoServiceManager(UserService<UserProtoConfiguration.UserMessage> userService, CommonUserService commonUserService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService) {
        super(userService, commonUserService, accountService, userRoleService);
    }
}
