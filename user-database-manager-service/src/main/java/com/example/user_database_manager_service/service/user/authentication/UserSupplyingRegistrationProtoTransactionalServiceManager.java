package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.common.CommonUserService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSupplyingRegistrationProtoTransactionalServiceManager extends UserSupplyingRegistrationProtoService {
    public UserSupplyingRegistrationProtoTransactionalServiceManager(UserService<UserProtoConfiguration.UserMessage> userService,
                                                                     CommonUserService commonUserService,
                                                                     AccountService<UserProtoConfiguration.AccountMessage> accountService,
                                                                     UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService) {
        super(userService, commonUserService, accountService, userRoleService);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        System.out.println("USER TRANSACTIONAL REGISTER");
        return super.register(registerModel);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteByEmail(String userEmail) {
        super.deleteByEmail(userEmail);
    }
}
