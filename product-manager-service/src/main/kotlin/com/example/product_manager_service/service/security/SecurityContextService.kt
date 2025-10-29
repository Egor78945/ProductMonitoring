package com.example.product_manager_service.service.security

import org.springframework.security.core.Authentication

interface SecurityContextService {
    fun getCurrentAuthentication() : Authentication
}