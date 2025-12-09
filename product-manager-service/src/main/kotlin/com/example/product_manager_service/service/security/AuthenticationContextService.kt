package com.example.product_manager_service.service.security

interface AuthenticationContextService<C> {
    fun getCurrentAuthentication() : C
}