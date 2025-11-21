package com.example.product_manager_service.service.product

import com.example.product_manager_service.service.security.AuthenticationContextService
import org.springframework.security.core.Authentication

abstract class ProductService<P>(protected val authContextService: AuthenticationContextService<Authentication>) {
    abstract fun getByAccountUuid(page: Int) : List<P>;
}