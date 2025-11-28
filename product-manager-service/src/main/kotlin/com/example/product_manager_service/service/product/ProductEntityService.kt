package com.example.product_manager_service.service.product

import com.example.product_manager_service.model.product.dto.ProductDto
import com.example.product_manager_service.service.security.AuthenticationContextService
import org.springframework.security.core.Authentication

abstract class ProductEntityService : ProductService<ProductDto>() {

}
