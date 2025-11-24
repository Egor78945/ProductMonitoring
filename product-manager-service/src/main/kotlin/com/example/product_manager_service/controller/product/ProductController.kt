package com.example.product_manager_service.controller.product

import com.example.product_manager_service.model.product.dto.ProductDto
import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.product.ProductRegistrationService
import com.example.product_manager_service.service.product.ProductService
import com.example.product_manager_service.service.security.AuthenticationContextService
import com.example.product_manager_service.util.grpc.mapper.GrpcMapper
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productRegistrationService: ProductRegistrationService<ProductPublisherDto>,
    private val productService: ProductService<ProductDto>,
    private val authenticationContextService: AuthenticationContextService<Authentication>
) {
    @PostMapping("/add")
    fun save(@RequestParam("url") productUrl: String?) =
        productRegistrationService.register(
            ProductPublisherDto(
                authenticationContextService.getCurrentAuthentication().principal.toString(),
                URI.create(productUrl ?: throw IllegalArgumentException("product url is invalid"))
            )
        )

    @GetMapping
    fun getProductsOfCurrentUser(@RequestParam("page") page: Int): ResponseEntity<List<ProductDto>> =
        ResponseEntity.ok(productService.getByAccountUuid(page))
}