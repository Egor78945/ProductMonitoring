package com.example.product_manager_service.service.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationContextServiceManager : AuthenticationContextService<Authentication> {
    override fun getCurrentAuthentication(): Authentication {
        return SecurityContextHolder.getContext().authentication ?: throw IllegalStateException("authentication is not present")
    }
}