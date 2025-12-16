package com.example.user_database_manager_service.service.user.operation.delete;

import com.example.user_database_manager_service.repository.user.common.CommonUserRepository;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.user.notification.common.CommonUserNotificationService;
import com.example.user_database_manager_service.service.user.role.common.CommonUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SafetyDeleteUserRepositoryTransactionalServiceManager extends SafetyDeleteUserRepositoryService {
    public SafetyDeleteUserRepositoryTransactionalServiceManager(CommonUserRepository commonUserRepository, CommonAccountProductService commonAccountProductService, CommonAccountService commonAccountService, CommonUserNotificationService commonUserNotificationService, CommonUserRoleService commonUserRoleService) {
        super(commonUserRepository, commonAccountProductService, commonAccountService, commonUserNotificationService, commonUserRoleService);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteByUuid(UUID uuid) {
        super.deleteByUuid(uuid);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteByEmail(String email) {
        super.deleteByEmail(email);
    }
}
