package com.example.user_database_manager_service.service.user.operation.delete;

import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.repository.user.common.CommonUserRepository;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.user.notification.common.CommonUserNotificationService;
import com.example.user_database_manager_service.service.user.role.common.CommonUserRoleService;

import java.util.UUID;

public abstract class SafetyDeleteUserRepositoryService extends DeleteUserRepositoryService {
    protected final CommonAccountProductService commonAccountProductService;
    protected final CommonAccountService commonAccountService;
    protected final CommonUserNotificationService commonUserNotificationService;
    protected final CommonUserRoleService commonUserRoleService;

    public SafetyDeleteUserRepositoryService(CommonUserRepository commonUserRepository, CommonAccountProductService commonAccountProductService, CommonAccountService commonAccountService, CommonUserNotificationService commonUserNotificationService, CommonUserRoleService commonUserRoleService) {
        super(commonUserRepository);
        this.commonAccountProductService = commonAccountProductService;
        this.commonAccountService = commonAccountService;
        this.commonUserNotificationService = commonUserNotificationService;
        this.commonUserRoleService = commonUserRoleService;
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        try {
            if (commonUserRepository.existsByUUID(uuid)) {
                commonAccountProductService.deleteAllByUserUuid(uuid);
                commonAccountService.deleteByUserUuid(uuid);
                commonUserNotificationService.deleteAllByUserUuid(uuid);
                commonUserRoleService.deleteAllByUserUuid(uuid);
                super.deleteByUuid(uuid);
            }
        } catch (Exception e) {
            throw new ProcessingException("failed to delete user");
        }
    }

    @Override
    public void deleteByEmail(String email) {
        try {
            if (commonUserRepository.existsByEmail(email)) {
                commonAccountProductService.deleteAllByUserEmail(email);
                commonAccountService.deleteByUserEmail(email);
                commonUserNotificationService.deleteAllByUserEmail(email);
                commonUserRoleService.deleteAllByUserEmail(email);
                super.deleteByEmail(email);
            }
        } catch (Exception e) {
            throw new ProcessingException("failed to delete user");
        }
    }
}
